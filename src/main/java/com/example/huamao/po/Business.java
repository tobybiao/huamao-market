package com.example.huamao.po;

import com.example.huamao.pojo.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/**mongodb 文档对象 商家信息
 * @author toby tobytb@163.com
 * @date 2018/5/8 21:15
 */
@Document(collection = "t_business")
public class Business {
    @Id
    private String id;
    private String username;
    private String phone;
    /**
     * 店铺介绍
     */
    private String introduce;
    /**
     * 评分
     */
    private String rating;
    /**
     * 详细地址
     */
    @Field("detailed_address")
    private String detailedAddress;
    /**
     * 经过hash加密过的密码
     */
    @Field("hashed_password")
    private String hashedPassword;

    /** 直接分类
     * <pre>
     {
         id: ObjectId("5a5e07d772cb620e64ef483b"),
         name: "男装专卖"
     }</pre>
     */
    private Map<String, String> category;

    /**
     * 祖先分类，不含直接分类 category_ancestors
     */
    @Field("category_ancestors")
    private List<Map<String, String>> categoryAncestors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Map<String, String> getCategory() {
        return category;
    }

    public void setCategory(Map<String, String> category) {
        this.category = category;
    }

    public List<Map<String, String>> getCategoryAncestors() {
        return categoryAncestors;
    }

    public void setCategoryAncestors(List<Map<String, String>> categoryAncestors) {
        this.categoryAncestors = categoryAncestors;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", introduce='" + introduce + '\'' +
                ", rating='" + rating + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", category=" + category +
                ", categoryAncestors=" + categoryAncestors +
                '}';
    }
}
