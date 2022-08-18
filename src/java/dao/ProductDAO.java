/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder().productId(rs.getInt(1))
                        .productName(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .price(rs.getInt(4))
                        .descript(rs.getString(5))
                        .imageUrl(rs.getString(6))
                        .createDate(rs.getDate(7))
                        .categoryId(rs.getInt(8))
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product where categoryId = ?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder().productId(rs.getInt(1))
                        .productName(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .price(rs.getInt(4))
                        .descript(rs.getString(5))
                        .imageUrl(rs.getString(6))
                        .createDate(rs.getDate(7))
                        .categoryId(rs.getInt(8))
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductsWithPagging(int page, int PAGE_SIZE) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product order by productId \n"
                    + "offset (?-1)*? row fetch next ? rows only ";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);

            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder().productId(rs.getInt(1))
                        .productName(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .price(rs.getInt(4))
                        .descript(rs.getString(5))
                        .imageUrl(rs.getString(6))
                        .createDate(rs.getDate(7))
                        .categoryId(rs.getInt(8))
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> search(String keyword) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product where productName like ?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder().productId(rs.getInt(1))
                        .productName(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .price(rs.getInt(4))
                        .descript(rs.getString(5))
                        .imageUrl(rs.getString(6))
                        .createDate(rs.getDate(7))
                        .categoryId(rs.getInt(8))
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalProducts() {
        try {
            String sql = "select count(*) from Product";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public Product getProductById(int productId) {
        try {
            String sql = "select * from Product where productId = ?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, productId);

            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder().productId(rs.getInt(1))
                        .productName(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .price(rs.getInt(4))
                        .descript(rs.getString(5))
                        .imageUrl(rs.getString(6))
                        .createDate(rs.getDate(7))
                        .categoryId(rs.getInt(8))
                        .build();
                return product;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void addNewProduct(Product product) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([productName]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[descript]\n"
                + "           ,[imageUrl]\n"
                + "           ,[createDate]\n"
                + "           ,[categoryId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getQuantity());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescript());
            ps.setString(5, product.getImageUrl());
            //convert
            //covert java.util.date -> java.sql.date
            java.sql.Date DateSql = new java.sql.Date(product.getCreateDate().getTime());
            ps.setDate(6, DateSql);

            ps.setInt(7, product.getCategoryId());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProduct(int productId) {
        String sql = "delete from Product where productId = ?";
        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, productId);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE Product\n"
                + "   SET [productName] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[descript] = ?\n"
                + "      ,[imageUrl] = ?\n"
                + "      ,[createDate] = ?\n"
                + "      ,[categoryId] = ?\n"
                + " WHERE [productId] = ?";
        try {
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getQuantity());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescript());
            ps.setString(5, product.getImageUrl());

            java.sql.Date DateSql = new java.sql.Date(product.getCreateDate().getTime());
            ps.setDate(6, DateSql);

            ps.setInt(7, product.getCategoryId());

            ps.setInt(8, product.getProductId());

            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public List<Product> getNewProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select top(4) * from Product order by createDate desc";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder().productId(rs.getInt(1))
                        .productName(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .price(rs.getInt(4))
                        .descript(rs.getString(5))
                        .imageUrl(rs.getString(6))
                        .createDate(rs.getDate(7))
                        .categoryId(rs.getInt(8))
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductBestSale() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "  select top(4) * from OrderDetails inner join Product on OrderDetails.productId = Product.productId order by OrderDetails.quantity desc";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = Product.builder()
                        .productId(rs.getInt(3))
                        .productName(rs.getString(9))
                        .quantity(rs.getInt(10))
                        .price(rs.getInt(11))
                        .descript(rs.getString(12))
                        .imageUrl(rs.getString(13))
                        .createDate(rs.getDate(14))
                        .categoryId(rs.getInt(15))
                        .build();
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        List<Product> list = d.getProductBestSale();
        for (Product product : list) {
            System.out.println(product);
        }
    }

}
