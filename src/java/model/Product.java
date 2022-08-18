/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.text.SimpleDateFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Product {

    private int productId;
    private String productName;
    private int quantity;
    private int price;
    private String descript;
    private String imageUrl;
    private Date createDate;
    private int categoryId;

    public String getStringDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(createDate);
    }

    public Product() {
    }

    public Product(int productId, String productName, int quantity, int price, String descript, String imageUrl, Date createDate, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.descript = descript;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
        this.categoryId = categoryId;
    }

}
