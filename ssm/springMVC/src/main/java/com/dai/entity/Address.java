package com.dai.entity;

/**
 * @author: dai.jf
 * @date: 2021/8/20 8:45
 * @description:
 */
public class Address {
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                '}';
    }
}
