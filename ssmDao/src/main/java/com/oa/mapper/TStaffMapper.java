package com.oa.mapper;

import com.oa.model.TStaff;
import com.oa.model.TStaffExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TStaffMapper {
    int countByExample(TStaffExample example);

    int deleteByExample(TStaffExample example);

    int deleteByPrimaryKey(String no);

    int insert(TStaff record);

    int insertSelective(TStaff record);

    List<TStaff> selectByExample(TStaffExample example);

    TStaff selectByPrimaryKey(String no);

    int updateByExampleSelective(@Param("record") TStaff record, @Param("example") TStaffExample example);

    int updateByExample(@Param("record") TStaff record, @Param("example") TStaffExample example);

    int updateByPrimaryKeySelective(TStaff record);

    int updateByPrimaryKey(TStaff record);
}