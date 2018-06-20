package com.example.huamao.po;

import com.example.huamao.pojo.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** mongodb 文档对象 商场收银员信息
 * @author toby tobytb@163.com
 * @date 2018/5/8 21:12
 */
@Document(collection="t_marketCashier")
public class MarketCashier {
    @Id
    private String id;
    private String username;
    private String email;
    private String phone;
    /**
     * 经过hash加密过的密码
     */
    private String hashed_password;
    /**
     * 地址
     */
    private Address address;

    /**
     * 详细地址
     */
    private String detailedAddress;

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

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

    @Override
    public String toString() {
        return "MarketCashier{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hashed_password='" + hashed_password + '\'' +
                ", address=" + address +
                ", detailedAddress='" + detailedAddress + '\'' +
                '}';
    }
}
