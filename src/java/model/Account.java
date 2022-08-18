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

public class Account {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String email;
    private int isAdmin;

    public Account() {
    }

    public Account(String username, String password, String name, String phone, String address, String email, int isAdmin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.isAdmin = isAdmin;
    }

}
