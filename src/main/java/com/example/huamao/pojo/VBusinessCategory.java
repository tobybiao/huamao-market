package com.example.huamao.pojo;

import java.util.List;

/** 视图层 入住商家分类
 * @author toby tobytb@163.com
 * @date 2018/5/29 20:09
 */
public class VBusinessCategory {
    private String name;
    private String description;
    List<String> parents; // 父分类  5a5e07d772cb620e64ef485f|服饰  list   英文|

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "VBusinessCategory{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parents=" + parents +
                '}';
    }
}
