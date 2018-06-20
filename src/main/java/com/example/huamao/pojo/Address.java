package com.example.huamao.pojo;

/** 地址信息
 * @author toby tobytb@163.com
 * @date 2018/5/2 17:45
 */
public class Address {
    /**
     * 街道、村
     */
    private String street;
    /**
     * 镇
     */
    private String town;
    /**
     * 县级市
     */
    private String subordinate_city;
    /**
     * 地级市
     */
    private String city;
    /**
     * 省份
     */
    private String province;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getSubordinate_city() {
        return subordinate_city;
    }

    public void setSubordinate_city(String subordinate_city) {
        this.subordinate_city = subordinate_city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", town='" + town + '\'' +
                ", subordinate_city='" + subordinate_city + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
