package com.oa.mapper;

import com.oa.model.TDepart;
import com.oa.model.TDepartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDepartMapper {
    int countByExample(TDepartExample example);

    int deleteByExample(TDepartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDepart record);

    int insertSelective(TDepart record);

    List<TDepart> selectByExample(TDepartExample example);

    TDepart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDepart record, @Param("example") TDepartExample example);

    int updateByExample(@Param("record") TDepart record, @Param("example") TDepartExample example);

    int updateByPrimaryKeySelective(TDepart record);

    int updateByPrimaryKey(TDepart record);
}