package com.example.huamao.po;

import com.example.huamao.pojo.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**mongodb 文档对象 商场管理员信息
 * @author toby tobytb@163.com
 * @date 2018/5/8 21:10
 */
@Document(collection="t_marketManager")
public class MarketManager {
    @Id
    private String id;
    private String username;
    private String email;
    private String phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "MarketManager{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
