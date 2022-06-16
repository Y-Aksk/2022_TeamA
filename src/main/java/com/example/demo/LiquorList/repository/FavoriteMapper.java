package com.example.demo.LiquorList.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * LiquorMapper
 */
 public interface FavoriteMapper {

//     @Select("select product_id, product_image,product_name from product LEFT OUTER JOIN favorite ON product.product_id = favorite.product_id where favorite.product_id = #{id}")
//      public List<FavoriteModel> findFavo(int id);

//      @Select("select * FROM product LEFT OUTER JOIN favorite ON product.product_id = favorite.product_id")
//      public List<FavoriteModel> findFavo1();

//     @Delete("delete from favorite where customer_id = #{id}")
//      public void delete(int id);
// }
@Select("select favorite.product_id, customer_id, product_image,product_name from product LEFT OUTER JOIN favorite ON product.product_id = favorite.product_id where favorite.customer_id = #{id} order by favorite.add_time")
    public List<FavoriteModel> findFavo(int id);

    @Delete("delete from favorite where favorite.product_id = #{productId}")
    public void deletefavorite(int productId);
 }
    


