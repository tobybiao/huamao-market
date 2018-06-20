package com.example.huamao.service.impl;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.db.MarketCashierRepository;
import com.example.huamao.po.MarketCashier;
import com.example.huamao.pojo.VMarketCashier;
import com.example.huamao.service.MarketCashierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**商场收银员服务
 * @author toby tobytb@163.com
 * @date 2018/5/23 23:20
 */
@Service
public class MarketCashierServiceImpl implements MarketCashierService {
    private static final Logger logger = LoggerFactory.getLogger(MarketCashierServiceImpl.class);
    private MarketCashierRepository marketCashierRepository;

    @Value("${USER_DEFAULT_PASSWORD}")
    private String DEFAULT_PASSWORD;
    @Autowired
    public MarketCashierServiceImpl(MarketCashierRepository marketCashierRepository) {
        this.marketCashierRepository = marketCashierRepository;
    }

    /**
     * 添加商场收银员
     *
     * @param marketCashier 　视图层传来的商场收银员信息
     * @return 　GenericResult　包含添加信息的响应数据
     */
    @Override
    public GenericResult saveMarketCashier(VMarketCashier marketCashier) {
        MarketCashier marketCashier1 = new MarketCashier();
        marketCashier1.setUsername(marketCashier.getUsername());
        marketCashier1.setEmail(marketCashier.getEmail());
        marketCashier1.setPhone(marketCashier.getPhone());
        marketCashier1.setDetailedAddress(marketCashier.getAddress());
        String md5Password = "";
        try {
            md5Password = DigestUtils.md5DigestAsHex(this.DEFAULT_PASSWORD.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        marketCashier1.setHashed_password(md5Password);
        this.marketCashierRepository.save(marketCashier1);
        logger.info("保存到数据库的收银员信息：" + marketCashier1);
        return GenericResult.ok(marketCashier1);
    }

    /**
     * 通过分页查找所有商场收银员信息
     *
     * @param pageIndex 页码
     * @param pageSize  每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByPage(int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        if(pageIndex < 0 || pageSize <= 0 ) {
            result.put("totalElements", 0);
            result.put("totalPages", 0);
            result.put("content", null);
            return result;
        }
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<MarketCashier> marketCashierPage = this.marketCashierRepository.findAll(pageable);
        result.put("totalElements", marketCashierPage.getTotalElements());
        result.put("totalPages", marketCashierPage.getTotalPages());
        result.put("content", this.marketCashierListToVMarketCashierList(marketCashierPage.getContent()));
        logger.info("在数据库中分页查找商场收银员返回数据：" + result);
        return result;
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
    public Map<String, Object> findAllByNameAndPage(final String name, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);

        MarketCashier marketCashier = new MarketCashier();
        marketCashier.setUsername(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", match -> match.ignoreCase().contains());

        Example<MarketCashier> example = Example.of(marketCashier, matcher);
        Page<MarketCashier> marketCashierPage = this.marketCashierRepository.findAll(example, pageable);
        result.put("totalElements", marketCashierPage.getTotalElements());
        result.put("totalPages", marketCashierPage.getTotalPages());
        result.put("content", this.marketCashierListToVMarketCashierList(marketCashierPage.getContent()));
        logger.info("在数据库中通过指定用户名分页查找商场收银员返回数据：" + result);
        return result;
    }

    /**
     * 收银员数据库格式转视图格式
     * @param dbMarketCashiers　数据库数据列表
     * @return 　List<VMarketCashier> 视图层收银员列表
     */
    private List<VMarketCashier> marketCashierListToVMarketCashierList (List<MarketCashier> dbMarketCashiers) {
        List<VMarketCashier> resultList = new ArrayList<>();
        for(MarketCashier dbMarketCashier: dbMarketCashiers) {
            VMarketCashier marketCashier = new VMarketCashier();
            marketCashier.setUsername(dbMarketCashier.getUsername());
            marketCashier.setAddress(dbMarketCashier.getDetailedAddress());
            marketCashier.setEmail(dbMarketCashier.getEmail());
            marketCashier.setPhone(dbMarketCashier.getPhone());
            resultList.add(marketCashier);
        }
        return resultList;
    }
}
