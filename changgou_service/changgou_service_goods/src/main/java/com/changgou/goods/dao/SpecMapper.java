package com.changgou.goods.dao;

import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecMapper extends Mapper<Spec> {

    @Select("SELECT NAME,options FROM tb_spec WHERE template_id in (SELECT template_id FROM tb_category WHERE NAME = #{categoryName})ORDER BY seq")
    public List<Map> findListByCategoryName(@Param("categoryName") String categoryName);


}
