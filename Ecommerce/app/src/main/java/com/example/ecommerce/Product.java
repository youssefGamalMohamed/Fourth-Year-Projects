package com.example.ecommerce;

public class Product {
    String ProductName;
    int ProductPrice , ProductQuantity , ProductImageSrc;

    public Product(String productName, int productPrice, int productQuantity, int productImageSrc) {
        ProductName = productName;
        ProductPrice = productPrice;
        ProductQuantity = productQuantity;
        ProductImageSrc = productImageSrc;
    }
}
