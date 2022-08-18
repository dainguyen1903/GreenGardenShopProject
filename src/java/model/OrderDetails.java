/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class OrderDetails {

    private int orderDetailsId;
    private int orderId;
    private int productId;
    private String productName;
    private String imageUrl;
    private int price;
    private int quantity;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailsId, int orderId, int productId, String productName, String imageUrl, int price, int quantity) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

}
