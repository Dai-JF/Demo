package com.dai.entity;

/**
 * @author: dai.jf
 * @date: 2021/8/20 8:38
 * @description:
 */
public class User {
    private String username;
    private String password;
    private Address address;

    public String getUsername() {
        return username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }
}
