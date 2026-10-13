package com.ecommerce;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Product {
    public static final String URL="jdbc:mysql://localhost:3306/ecommerce_db";
    public static final String USER="root";
    public static final String PASS="2330";
    public void viewProduct()
    {
       String sql="select product_id,product_name,price,stock_quantity from products";
       try(
           Connection con=DriverManager.getConnection(URL,USER,PASS);
           PreparedStatement pstmt=con.prepareStatement(sql);
           ResultSet rs=pstmt.executeQuery();
       ){
           System.out.println("\n==========Available Products===========");
           System.out.println("Product ID\tProduct Name\tPrice\tStock Quantity\n");
           System.out.println("----------------------");
           boolean found=false;
           while(rs.next()){
               found=true;
               int id=rs.getInt("product_id");
               String name=rs.getString("product_name");
               double price=rs.getDouble("price");
               int stock=rs.getInt("stock_quantity");
               System.out.printf("%-10d %-20s %-12.2f %-10d%n",
                       id, name, price, stock);
           }
           if(!found){
               System.out.println("Nothing found");
           }
       }catch (SQLException e){
               System.out.println("Error retrieving products.");
               e.printStackTrace();

       }
    }
    public void AddProduct(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter product name:");
        String name=sc.nextLine();
        System.out.println("Enter product price:");
        double price=sc.nextDouble();
        System.out.println("Enter available stock : ");
        int stock=sc.nextInt();
        if(stock<=0||price<=0){
            System.out.println("Price must be positive");
            return;
        }
        String sql="Insert into products(product_name,price,stock_quantity) values(?,?,?)";
        try(
                Connection con= DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement pstmt=con.prepareStatement(sql)
        ) {
            pstmt.setString(1,name);
            pstmt.setDouble(2,price);
            pstmt.setInt(3,stock);
            int rows=pstmt.executeUpdate();
            if (rows>0){
                System.out.println("Product added successfully");
            } }
        catch(SQLException e){
            System.out.println("Error adding product");
            e.printStackTrace();

        }
    }

}