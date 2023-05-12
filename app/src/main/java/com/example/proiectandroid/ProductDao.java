package com.example.proiectandroid;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ProductDao {
    @Insert
    void insertAllProducts(Product... products);

    @Query("SELECT * FROM product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product WHERE id_product = :idProduct")
    List<Product> getProductById(int idProduct);

}
