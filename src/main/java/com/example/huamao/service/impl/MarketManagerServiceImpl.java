package com.example.huamao.service.impl;

import com.example.huamao.db.MarketManagerRepository;
import com.example.huamao.po.MarketManager;
import com.example.huamao.pojo.VMarketManager;
import com.example.huamao.service.MarketManagerService;
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

/**
 * @author toby tobytb@163.com
 * @date 2018/5/29 9:09
 */
@Service
public class MarketManagerServiceImpl implements MarketManagerService {
    private static final Logger logger = LoggerFactory.getLogger(MarketManagerServiceImpl.class);
    private MarketManagerRepository marketManagerRepository;
    @Value("${USER_DEFAULT_PASSWORD}")
    private String DEFAULT_PASSWORD;

    @Autowired
    public MarketManagerServiceImpl(MarketManagerRepository marketManagerRepository) {
        this.marketManagerRepository = marketManagerRepository;
    }

    /**
     * 添加商场管理员信息
     *
     * @param vMarketManager 视图层传来的数据
     * @return MarketManager 保存到数据库后的商场管理员信息
     */
    @Override
    public MarketManager saveMarketManager(VMarketManager vMarketManager) {
        MarketManager marketManager = new MarketManager();
        marketManager.setUsername(vMarketManager.getUsername());
        marketManager.setEmail(vMarketManager.getEmail());
        marketManager.setPhone(vMarketManager.getPhone());
        marketManager.setDetailedAddress(vMarketManager.getAddress());
        String md5Password = "";
        try {
            md5Password = DigestUtils.md5DigestAsHex(this.DEFAULT_PASSWORD.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        marketManager.setHashedPassword(md5Password);
        this.marketManagerRepository.save(marketManager);
        logger.info("添加到数据库的商场管理员信息：" + marketManager);
        marketManager.setHashedPassword("");
        return marketManager;
    }

    /**
     * 通过分页查找所有商场管理员信息
     *
     * @param pageIndex 页码
     * @param pageSize  每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByPage(int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<MarketManager> marketManagers = this.marketManagerRepository.findAll(pageable);
        result.put("totalElements", marketManagers.getTotalElements());
        result.put("totalPages", marketManagers.getTotalPages());
        result.put("content", this.marketManagerListToVMarketManagerList(marketManagers.getContent()));
        logger.info("在数据库中分页查找商场管理员信息返回给视图数据：" + result);
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
    public Map<String, Object> findAllByNameAndPage(String name, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);

        MarketManager marketManager = new MarketManager();
        marketManager.setUsername(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", match -> match.ignoreCase().contains());

        Example<MarketManager> marketManagerExample = Example.of(marketManager, matcher);
        Page<MarketManager> marketManagers = this.marketManagerRepository.findAll(marketManagerExample, pageable);
        result.put("totalElements", marketManagers.getTotalElements());
        result.put("totalPages", marketManagers.getTotalPages());
        result.put("content", this.marketManagerListToVMarketManagerList(marketManagers.getContent()));
        logger.info("在数据库中通过指定用户名分页查找商场管理员返回到视图的数据：" + result);
        return result;
    }

    private List<VMarketManager> marketManagerListToVMarketManagerList(List<MarketManager> marketManagerList) {
        List<VMarketManager> vMarketManagerList = new ArrayList<>();
        for(MarketManager marketManager: marketManagerList) {
            VMarketManager vMarketManager = new VMarketManager();
            vMarketManager.setUsername(marketManager.getUsername());
            vMarketManager.setPhone(marketManager.getPhone());
            vMarketManager.setEmail(marketManager.getEmail());
            vMarketManager.setAddress(marketManager.getDetailedAddress());
            vMarketManagerList.add(vMarketManager);
        }
        return vMarketManagerList;
    }
}
