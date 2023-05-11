package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BrandMapper extends Mapper<Brand> {

    /*
    根据分类名称查询品牌列表
     */
    @Select("SELECT name,image FROM tb_brand WHERE id in (SELECT brand_id from tb_category_brand WHERE category_id in ( select id from tb_category WHERE name = #{name}))ORDER BY seq")
    public List<Map> findListByCategoryName(@Param("name") String categoryName);

}
