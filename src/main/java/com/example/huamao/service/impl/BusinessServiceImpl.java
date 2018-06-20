package com.example.huamao.service.impl;

import com.example.huamao.db.BusinessRepository;
import com.example.huamao.po.Business;
import com.example.huamao.pojo.VBusiness;
import com.example.huamao.pojo.VBusinessListNode;
import com.example.huamao.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/30 17:43
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    private static final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
    private BusinessRepository businessRepository;
    @Value("${USER_DEFAULT_PASSWORD}")
    private String DEFAULT_PASSWORD;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    /**
     * 把是视图层传来的入住商家分类信息转换成数据库保存分类的格式
     * @param categoryList 入住商家分类 5a5e07d772cb620e64ef485f|服饰 的list
     * @return  <pre>{
                        id: ObjectId("5a5e07d772cb620e64ef483b"),
                        name: "男装专卖"
                    }</pre>
     */
    private List<Map<String, String>> AddViewBusinessCategoryToPOBusinessList(List<String> categoryList) {
        List<Map<String, String>> resultList = new ArrayList<>();
        for(String categoryIdAndName: categoryList) {
            String[] categoryIdAndNameArr = categoryIdAndName.split("\\|"); // 数据格式：5a5e07d772cb620e64ef482e|男装专卖
            Map<String, String> resultNode = new HashMap<>();
            if(categoryIdAndNameArr.length == 2) {
                resultNode.put("id", categoryIdAndNameArr[0]);
                resultNode.put("name", categoryIdAndNameArr[1]);
                resultList.add(resultNode);
            } else {
                logger.error("添加入住商家视图传来的入住商家分类信息格式不正确");
                throw new RuntimeException("添加入住商家视图传来的入住商家分类信息格式不正确");
            }
        }
        return resultList;
    }

    /**
     * 从视图层传来的入住商家分类信息中提取祖先分类信息，不含直接分类
     * @param categoryList 从视图层传来的入住商家分类信息
     * @return 祖先分类信息，不含直接分类
     */
    private List<Map<String, String>> getCategoryAncestorsFromCategoryList(List<Map<String, String>> categoryList) {
        List<Map<String, String>> categoryAncestors = new ArrayList<>(); // 不含直接分类
        for (int i = 0; i < categoryList.size() - 1; i++) {
            categoryAncestors.add(categoryList.get(i));
        }
        return  categoryAncestors;
    }
    /**
     * 添加入住商家
     *
     * @param vBusiness 　视图层传来的入住商家信息
     * @return 　Business　保存到数据库的入住商家信息
     */
    @Override
    public Business saveBusiness(VBusiness vBusiness) {
        Business business = new Business();
        business.setUsername(vBusiness.getBusinessName());
        business.setPhone(vBusiness.getPhone());
        business.setIntroduce(vBusiness.getIntro());
        business.setDetailedAddress(vBusiness.getAddress());
        String rating = vBusiness.getRating() + "";
        business.setRating(String.format(rating, "%1.2f")); // 设置保留位数
        String md5Password = "";
        try {
            md5Password = DigestUtils.md5DigestAsHex(this.DEFAULT_PASSWORD.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        business.setHashedPassword(md5Password);

        List<Map<String, String>> businessCat = this.AddViewBusinessCategoryToPOBusinessList(vBusiness.getCategory());
        int businessCatSize = businessCat.size();
        business.setCategory(businessCat.get(businessCatSize - 1)); // 直接分类为最后一个元素
        if(businessCatSize > 1) {
            //存在不包含直接分类的祖先分类
            business.setCategoryAncestors(this.getCategoryAncestorsFromCategoryList(businessCat));
        }
        this.businessRepository.save(business);
        business.setHashedPassword("");
        logger.info("添加到数据库的入住商家信息如下：" + business);
        return business;
    }

    /**
     * 通过分页查找所有入住商家信息
     *
     * @param pageIndex 页码
     * @param pageSize  每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByPage(int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Business> businessPage = this.businessRepository.findAll(pageable);
        result.put("totalElements", businessPage.getTotalElements());
        result.put("totalPages", businessPage.getTotalPages());
        result.put("content", this.PoBusinessListToVBusinessListNodeList(businessPage.getContent()));
        logger.info("在数据库中分页查找入住商家信息返回给视图数据：" + result);
        return result;
    }

    /**
     * 将数据库中商家信息列表转换为视图层商家列表
     * @param businessList 数据库中商家信息列表
     * @return List<VBusinessListNode> 商家列表视图列表数据
     */
    private List<VBusinessListNode> PoBusinessListToVBusinessListNodeList(List<Business> businessList) {
        List<VBusinessListNode> resultList = new ArrayList<>();
        for(Business business: businessList) {
            VBusinessListNode node = new VBusinessListNode();
            node.setId(business.getId());
            node.setBusinessName(business.getUsername());
            node.setPhone(business.getPhone());
            node.setAddress(business.getDetailedAddress());
            node.setIntro(business.getIntroduce());
            node.setRating(business.getRating());
            node.setSalesVolume(200); // todo 商家销量

            List<String> catList = new ArrayList<>(); // 分类名称List
            List<Map<String, String>> categoryAncestors = business.getCategoryAncestors();
            if(null != categoryAncestors && categoryAncestors.size() > 0) { // 从祖先分类开始
                for(Map<String, String> ancestor: business.getCategoryAncestors()) {
                    catList.add(ancestor.get("name"));
                }
            }
            catList.add(business.getCategory().get("name"));
            node.setCategory(catList);

            resultList.add(node);
        }
        return resultList;
    }
    /**
     * 通过用户名查找用户信息，结果分页展示
     *
     * @param name      用户名
     * @param pageIndex 页码 0开始
     * @param pageSize  每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByNameAndPage(String name, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);

        Business business = new Business();
        business.setUsername(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", match -> match.ignoreCase().contains());

        Example<Business> businessExample = Example.of(business, matcher);

        Page<Business> businessPage = this.businessRepository.findAll(businessExample, pageable);
        result.put("totalElements", businessPage.getTotalElements());
        result.put("totalPages", businessPage.getTotalPages());
        result.put("content", this.PoBusinessListToVBusinessListNodeList(businessPage.getContent()));
        logger.info("在数据库中通过指定用户名分页查找商场入住商家返回到视图的数据：" + result);
        return result;
    }

    /**
     * 通过id 修改入住商家信息
     *
     * @param vBusinessListNode 视图层 商家列表中单个商家
     * @return Business 保存到数据库中的商家信息
     */
    @Override
    public Business updateBusinessById(VBusinessListNode vBusinessListNode) {
        Business business = this.businessRepository.findOne(vBusinessListNode.getId());
        logger.info("更新商家信息前：" + business);
        business.setUsername(vBusinessListNode.getBusinessName());
        business.setIntroduce(vBusinessListNode.getIntro());
        business.setRating(vBusinessListNode.getRating());
        business.setDetailedAddress(vBusinessListNode.getAddress());
        business.setPhone(vBusinessListNode.getPhone());

        // 视图层传来的分类数据
        List<Map<String, String>> catListFromAddView = this.AddViewBusinessCategoryToPOBusinessList(vBusinessListNode.getCategory());
        if(catListFromAddView != null && catListFromAddView.size() > 0) {
            // 存在父分类
            business.setCategory(catListFromAddView.get(catListFromAddView.size()-1)); // 最后一个为直接父分类
            if(catListFromAddView.size() > 1) {
                // 存在祖先分类
                business.setCategoryAncestors(this.getCategoryAncestorsFromCategoryList(catListFromAddView));
            } else {
                // 不存祖先分类
                business.setCategoryAncestors(null);
            }
        } else {
            // 不存在父分类 实际上任何商家都有一个分类 所以这段是不会执行的
            business.setCategory(null);
        }
        this.businessRepository.save(business);
        logger.info("更新到数据库的信息为：" + business);
        return business;
    }
}
