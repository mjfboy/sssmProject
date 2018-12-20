package com.oa.service;

import com.oa.mapper.TUserMapper;
import com.oa.model.*;
import com.oa.serviceInterface.UserServiceInterface;
import com.oa.utils.EncrypUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface{
    @Autowired
    private TUserMapper tUserMapper;
    @Override
    public TUser login(String no, String pass) {
        TUserExample t=new TUserExample();
        TUserExample.Criteria criteria = t.createCriteria();
        criteria.andNoEqualTo(no);
        criteria.andPasswordEqualTo(pass);

        List<TUser> list = tUserMapper.selectByExample(t);
        if(list==null||list.size()==0){
            return null;
        }
        TUser s=list.get(0);
        System.out.println("业务层"+s);
        return s;
    }


    @Override
    public List<TauthorityVo> queryMenu(Integer id) {
        //查询所有一级菜单
        List<TauthorityVo> list=tUserMapper.queryMenu(id);

        /*
        * 查询父菜单的子菜单
        * */
        for (TauthorityVo t:list){
            t.setChilds(tUserMapper.queryChildMenu(t.getId()));
        }

        return list;
    }


    //上传成功后做更新
    @Override
    public int updateById(String path, Integer id) {
        TUser u=new TUser();
        u.setHeadphoto(path);
        u.setId(id);
        int i = tUserMapper.updateByPrimaryKeySelective(u);
        return i;
    }


    @Override
    public int updatePassword(Integer id, String oldpassword, String password) {
        String opd= EncrypUtil2.md5Pass(oldpassword);
        String npd= EncrypUtil2.md5Pass(password);
        System.out.println("旧密码==="+opd+"新"+npd);
        TUserExample t=new TUserExample();
        TUserExample.Criteria criteria = t.createCriteria();
        criteria.andIdEqualTo(id)
                .andPasswordEqualTo(opd);
        List<TUser> list = tUserMapper.selectByExample(t);
        if(list==null||list.size()==0){//旧密码不正确
            return 3;
        }
        else {//密码正确做更新
            TUser user=new TUser();
            user.setId(id);
            user.setPassword(npd);
            int i = tUserMapper.updateByPrimaryKeySelective(user);
            return i;
        }

    }

    @Override
    public void xiugai(TUser user) {
        int i = tUserMapper.updateByPrimaryKeySelective(user);

    }


    @Override
    public TUser queryUser(Integer id) {
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TUserRoleVo> queryAllURv(Integer page, Integer limit,Integer flag,String no) {
        return tUserMapper.queryAllURv( page, limit,flag,no);
    }


    @Override
    public int Usercount() {
        return tUserMapper.countByExample(null);
    }

    @Override
    public List<TUserRoleVo> queryTUserRoleVo(Integer page, Integer limit, Integer flag, String no) {
        List<TUserRoleVo> list=tUserMapper.queryAllURv(page,limit, flag,no);
        for (TUserRoleVo r:list){
            //用户所拥有的角色集合
            List<TRole> roleList = r.getRoleList();
            for (TRole t : roleList) {
                r.getRids().add(t.getId());
                r.getRole().add(t.getInfo());
                r.getRnames().add(t.getName());

            }

        }
        return list;

    }

    @Override
    public int updateFlag(Integer id, Integer flag) {
        int i=tUserMapper.updateFlag(id,flag);
        return i;
    }

    @Override
    public int delById(Integer id) {
        return tUserMapper.delById(id);
    }

    @Override
    public int delAndins(Integer id, Integer[] rids) {
        return tUserMapper.add(id,rids);
    }

    @Override
    public List<TRole> queryrole(Integer page, Integer limit ) {
        List<TRole> list=tUserMapper.queryrole(page,limit);
        for (TRole r:list){
            //用户所拥有的角色集合
            List<TRole> roleList = new ArrayList<>();
            for (TRole t : roleList) {
                r.setId(t.getId());
                r.setInfo(t.getInfo());
                r.setName(t.getName());
            }

        }
        return list;
    }


    @Override
    public List<TRole> queryAllRoles() {
        return tUserMapper.queryAllRoles();
    }

    @Override
    public TRole queryroleid(Integer rid) {
        return tUserMapper.queryroleid(rid);
    }

    @Override
    public int roleup(String name, String info, String rid) {
        return tUserMapper.roleup(name,info,rid);
    }

    @Override
    public int roledel(Integer id) {
        return tUserMapper.roledel(id);
    }

    @Override
    public List<ZtreeVo> queryRoleZtree() {

        List<ZtreeVo> ztreeVos = tUserMapper.queryRoleZtree();
        ResultVo resultVo = new ResultVo();
            resultVo.setData(ztreeVos);
        return ztreeVos;
    }

    @Override
    public int inserrole(Integer rid, Integer[] aid) {

        return tUserMapper.inserrole(rid,aid);
    }

    @Override
    public List<TAuthority> ta() {


        return tUserMapper.queryauth();
    }

    @Override
    public int cont() {
        return tUserMapper.cont();
    }

}
