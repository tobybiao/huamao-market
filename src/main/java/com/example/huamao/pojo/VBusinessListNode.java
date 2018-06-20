package com.example.huamao.pojo;

import java.util.List;

/** 视图 商家列表节点
 * @author toby tobytb@163.com
 * @date 2018/5/30 23:29
 */
public class VBusinessListNode {
    private String id;
    private String businessName;
    /**
     * 商家介绍
     */
    private String intro;

    /**
     * 商家店铺位置
     */
    private String address;

    private String phone;

    /**
     * 商家店铺评分
     */
    private String rating;

    /**
     * 店铺销量
     */
    private Integer salesVolume;

    /**
     * 店铺分类 ['服装店', '男装专卖']
     */
    private List<String> category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "VBusinessListNode{" +
                "id='" + id + '\'' +
                ", businessName='" + businessName + '\'' +
                ", intro='" + intro + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", rating='" + rating + '\'' +
                ", salesVolume=" + salesVolume +
                ", category=" + category +
                '}';
    }
}
