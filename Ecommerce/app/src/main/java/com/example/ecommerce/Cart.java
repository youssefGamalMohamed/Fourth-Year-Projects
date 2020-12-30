package com.example.ecommerce;

import java.util.Hashtable;

public class Cart {
    public static int CustID , OrdID;
    public static Hashtable<Integer , Integer> product_and_quantity = new Hashtable<Integer, Integer>();

    public static void AddProductToCart(int ProductId)
    {
        if(!product_and_quantity.containsKey(ProductId)){
            product_and_quantity.put(ProductId , 1);
        }
        else{
            int new_quantity = product_and_quantity.get(ProductId) + 1;
            UpdateProductToCart(ProductId , new_quantity);
        }
    }

    public static void UpdateProductToCart(int ProductId , int Quantity){
        if(product_and_quantity.containsKey(ProductId)){
            product_and_quantity.put(ProductId ,  Quantity);
        }
    }

    public static void DeleteProductFromCart(int ProductId){
        if(product_and_quantity.containsKey(ProductId)){
            product_and_quantity.remove(ProductId);
        }
    }

}
