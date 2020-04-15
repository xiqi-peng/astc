package com.astc.shangpin.dao;
import com.astc.shangpin.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {
    @Select("SELECT tb.`id`,tb.`name` FROM tb_category_brand cb,tb_category tb WHERE cb.`category_id`= #{categoryId} AND cb.`brand_id`=tb.`id`")
    List<Brand> findBrandsByCategoryId(@Param("categoryId") Integer categoryId);
}
