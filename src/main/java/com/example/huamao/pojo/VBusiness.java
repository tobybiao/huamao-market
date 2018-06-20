package com.example.huamao.pojo;

import java.util.List;

/** 视图层添加入住商家信息
 * @author toby tobytb@163.com
 * @date 2018/5/30 9:11
 */
public class VBusiness {
    /**
     * 商家名称
     */
    private String businessName;

    /**
     * 入住商家介绍
     */
    private String intro;

    /**
     * 入住商家位置
     */
    private String address;

    /**
     * 商家联系电话
     */
    private String phone;

    /**
     * 入住商家分类 5a5e07d772cb620e64ef485f|服饰
     */
    private List<String> category;

    /**
     * 入住商家评分
     */
    private float rating;

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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "VBusiness{" +
                "businessName='" + businessName + '\'' +
                ", intro='" + intro + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", category=" + category +
                ", rating=" + rating +
                '}';
    }
}
