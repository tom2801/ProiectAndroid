package com.example.proiectandroid;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

public interface CartItemDao {
    @Insert
    void insertAll(CartItem... cartItems);

    @Query("SELECT * FROM user JOIN cartitem ON (user.id_user = cartitem.id_user) "+
    " JOIN product ON (product.id_product = cartitem.id_cart_item) WHERE user.id_user LIKE :idUser"
    )
    Map<User,Map<CartItem,List<Product>>> getCartByUserId(int idUser);
}
