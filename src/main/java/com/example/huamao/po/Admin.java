package com.example.huamao.po;

import com.example.huamao.pojo.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** mongodb 文档对象 超级管理员
 * @author toby tobytb@163.com
 * @date 2018/5/2 17:42
 */
@Document(collection = "t_admin")
public class Admin {
    @Id
    private String id;
    private String username;
    private String email;
    private String phone;
    /**
     * 详细地址
     */
    private String detailedAddress;
    /**
     * 经过hash加密过的密码
     */
    private String hashed_password;
    /**
     * 地址
     */
    private Address address;

    public String getId() {
        return id;
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

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", hashed_password='" + hashed_password + '\'' +
                ", address=" + address +
                '}';
    }
}
