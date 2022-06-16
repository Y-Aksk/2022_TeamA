package com.example.demo.LiquorList.repository;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface LiquorMapper {


    @Select("select product_id, product_name, product_image, product_price, abv, volume, product_detail from product order by product_id asc")
    public List<LiquorModel> findall();

    @Select("select product_id, product_name, product_image, product_price, abv, volume, product_detail from product where category_id = 2 order by product_id asc")
    public List<LiquorModel> findbrandy();

    @Select("select product_id, product_name, product_image, product_price, abv, volume, product_detail from product where category_id = 4 order by product_id asc")
    public List<LiquorModel> findgin();

    @Select("select product_id, product_name, product_image, product_price, abv, volume, product_detail from product where category_id = 3 order by product_id asc")
    public List<LiquorModel> findrum();

    @Select("select product_id, product_name, product_image, product_price, abv, volume, product_detail from product where category_id = 1 order by product_id asc")
    public List<LiquorModel> findwhisky();


    @Select("select product_id, product_name, product_image, product_price, abv, volume from product where product_name like #{name} order by product_id asc")
    public List<LiquorModel> findName(String name);

    @Select("select product_id, product_name, product_image, product_price, abv, volume, product_detail from product where product_id = #{id}")
    public LiquorModel findone(int id);

    //カート
    @Insert("insert into cart (customer_id, product_id, count, product_price) VAlUES(#{customer_id}, #{product_id}, 1, #{product_price})")
    public void insertcart(
        @Param("product_id")int product_id,
        @Param("product_price")int product_price,
        @Param("count") int count
        
    );
    @Select("select product_price from product where product_id = #{id} ")
    public int findprice(int id);


     @Insert("insert into favorite (customer_id, product_id) VAlUES(#{customer_id}, #{product_id})")
     public void insertfavorite(
         @Param("product_id")int product_id,
         @Param("customer_id")int customer_id

     );

     
}