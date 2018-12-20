package com.oa.mapper;

import com.oa.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    List<TauthorityVo> queryChildMenu(Integer uid);


    List<TauthorityVo> queryMenu(Integer id);

    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUserRoleVo> queryAllURv(@Param("begin") Integer page, @Param("limit") Integer limit, @Param("flag") Integer flag, @Param("no") String no);

    int updateFlag(@Param("id") Integer id, @Param("flag") Integer flag);

    int delById(Integer id);

    int add(@Param("uid") Integer id, @Param("list") Integer[] rids);

    List<TRole> queryAllRoles();

    List<TRole> queryrole(@Param("page") Integer page, @Param("limit") Integer limit);

    TRole queryroleid(Integer rid);

    int roleup(@Param("name") String name, @Param("info") String info, @Param("rid") String rid);

    int roledel(@Param("id") Integer id);

    List<ZtreeVo> queryRoleZtree();

    int inserrole(@Param("rid") Integer rid, @Param("aids") Integer[] cid);

    List<TAuthority> queryauth();
    int cont();
}