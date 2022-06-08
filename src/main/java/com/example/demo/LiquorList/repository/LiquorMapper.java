package com.example.demo.LiquorList.repository;
import java.util.List;

import org.apache.ibatis.annotations.Select;


public interface LiquorMapper {


    @Select("select product_name, product_image, product_price, abv, volume from product order by product_id asc")
    public List<LiquorModel> findall();

    @Select("select product_name, product_image, product_price, abv, volume from product where product_name like #{name} order by product_id asc")
    public List<LiquorModel> findName(String name);

    @Select("select product_name, product_image, product_price, abv, volume, product_detil from product where product_id = #{id}")
    public List<LiquorModel> findone(int id);
}