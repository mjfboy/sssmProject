package com.oa.mapper;

import com.oa.model.TUserroleExample;
import com.oa.model.TUserroleKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserroleMapper {
    int countByExample(TUserroleExample example);

    int deleteByExample(TUserroleExample example);

    int deleteByPrimaryKey(TUserroleKey key);

    int insert(TUserroleKey record);

    int insertSelective(TUserroleKey record);

    List<TUserroleKey> selectByExample(TUserroleExample example);

    int updateByExampleSelective(@Param("record") TUserroleKey record, @Param("example") TUserroleExample example);

    int updateByExample(@Param("record") TUserroleKey record, @Param("example") TUserroleExample example);
}