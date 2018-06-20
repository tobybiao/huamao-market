package com.example.huamao.pojo;

import java.util.List;

/** 视图层商场商品分类节点
 * @<code>
{
    value: '5a5e07d772cb620e64ef485f,食品', // ObjectId
    label: '食品',
    children: [
        {
            value: '5a5e07d772cb620e64ef487k,新鲜水果',
            label: '新鲜水果',
            children: [
                {
                value: '5a5e07d772cb620e64ef488e,苹果',
                label: '苹果',
                    children: [
                        {
                        value: '5a5e07d772cb620e64ef483b,红富士',
                        label: '红富士'
                        },
                        {
                        value: '5a5e07d772cb620e64ef483a,青苹果',
                        label: '青苹果'
                        }
                    ]
                }
            ]
        }
    ]
}
 * </code>
 *
 * @author toby tobytb@163.com
 * @date 2018/5/17 16:46
 */
public class MarketGoodsCategoryTreeNode {
    private String value;
    private String label;
    private List<MarketGoodsCategoryTreeNode> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MarketGoodsCategoryTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<MarketGoodsCategoryTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MarketGoodsCategoryTreeNode{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
