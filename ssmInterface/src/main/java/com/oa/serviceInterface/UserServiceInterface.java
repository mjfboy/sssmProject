package com.oa.serviceInterface;

import com.oa.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserServiceInterface {
    TUser login(String no, String pass);

    List<TauthorityVo> queryMenu(Integer id);

    int updateById(String path, Integer id);

    int updatePassword(Integer id, String oldpassword, String password);

    void xiugai(TUser user);

    TUser queryUser(Integer id);

    List<TUserRoleVo> queryAllURv(Integer page, Integer limit, Integer flag, String no);

    int Usercount();

    List<TUserRoleVo> queryTUserRoleVo(Integer page, Integer limit, Integer flag, String no);

    int updateFlag(Integer id, Integer flag);

    int delById(Integer id);

    int delAndins(Integer id, Integer[] rids);

    List<TRole> queryrole(Integer page, Integer limit);

    List<TRole> queryAllRoles();

    TRole queryroleid(Integer rid);

    int roleup(String name, String info, String rid);

    int roledel(@Param("id") Integer id);

    List<ZtreeVo> queryRoleZtree();

    int inserrole(Integer rid, @Param("aids") Integer[] aid);

    List<TAuthority> ta();
    int cont();
}
