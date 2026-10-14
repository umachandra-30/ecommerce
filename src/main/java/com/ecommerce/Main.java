package com.ecommerce;
public class Main {

    public static void main(String[] args) {

        Product product = new Product();
        product.DeleteProduct();
        System.out.println("Product updated successfully");
        product.viewProduct();


    }
}
