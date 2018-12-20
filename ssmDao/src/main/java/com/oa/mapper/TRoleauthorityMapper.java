package com.oa.mapper;

import com.oa.model.TRoleauthorityExample;
import com.oa.model.TRoleauthorityKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRoleauthorityMapper {
    int countByExample(TRoleauthorityExample example);

    int deleteByExample(TRoleauthorityExample example);

    int deleteByPrimaryKey(TRoleauthorityKey key);

    int insert(TRoleauthorityKey record);

    int insertSelective(TRoleauthorityKey record);

    List<TRoleauthorityKey> selectByExample(TRoleauthorityExample example);

    int updateByExampleSelective(@Param("record") TRoleauthorityKey record, @Param("example") TRoleauthorityExample example);

    int updateByExample(@Param("record") TRoleauthorityKey record, @Param("example") TRoleauthorityExample example);
}