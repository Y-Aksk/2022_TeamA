package com.example.demo.LiquorList.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CartMapper {

    //データ取得時並び方は事前に指定おく必要あり   && cart.customer_id = 2
    @Select("select * from product LEFT OUTER JOIN cart ON product.product_id = cart.product_id order by cart.add_time")
    public List<CartModel>findCart();

    //cartの中身表示
    @Select("select cart.product_id, customer_id, product_image,product_name, cart.product_price, count from product LEFT OUTER JOIN cart ON product.product_id = cart.product_id where cart.customer_id =#{id} order by cart.table_id")
    public List<CartModel> findCartId(int id);


    @Delete("delete from cart where cart.product_id = #{productId}")
    public void deletecart(int productId);

    


    @Insert("insert into history(customer_id, product_id) values(#{customer_id},#{product_id})")
    public void inserthistory(
        @Param("customer_id")int customer_id,
         @Param("product_id")int product_id
         
     );


    //合計
    @Select("select SUM(product_price * count) from cart where customer_id = #{id}")
    public List<CartModel>findSum(int id);

    @Update("update cart set count = #{count} where product_id = #{product_id} ")
    public void update(
        @Param("product_id")int product_id,
        @Param("count")int count
    );

    @Delete("delete from cart where customer_id = #{id}")
    public void deletecomp(int id);
    
}
