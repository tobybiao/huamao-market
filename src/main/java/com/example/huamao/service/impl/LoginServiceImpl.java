package com.example.huamao.service.impl;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.common.pojo.ISessionKey;
import com.example.huamao.common.pojo.IUserType;
import com.example.huamao.db.AdminRepository;
import com.example.huamao.db.BusinessRepository;
import com.example.huamao.db.MarketCashierRepository;
import com.example.huamao.db.MarketManagerRepository;
import com.example.huamao.po.Admin;
import com.example.huamao.po.Business;
import com.example.huamao.po.MarketCashier;
import com.example.huamao.po.MarketManager;
import com.example.huamao.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/** 用户登录服务
 * @author toby tobytb@163.com
 * @date 2018/5/8 10:54
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private AdminRepository adminRepository;
    private MarketManagerRepository marketManagerRepository;
    private MarketCashierRepository marketCashierRepository;
    private BusinessRepository businessRepository;
    @Value("${LOGIN_NUMBER}")
    private String LOGIN_NUMBER;
    @Autowired
    public LoginServiceImpl(AdminRepository adminRepository, MarketManagerRepository marketManagerRepository, MarketCashierRepository marketCashierRepository, BusinessRepository businessRepository) {
        this.adminRepository = adminRepository;
        this.marketManagerRepository = marketManagerRepository;
        this.marketCashierRepository = marketCashierRepository;
        this.businessRepository = businessRepository;
    }

    /**
     * 用户通过用户名、密码、指定用户类型登录
     * 登录成功把用户登录信息保存在session中
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 登录请求结果，成功时候返回token
     */
    @Override
    public GenericResult login(String username, String password, String userType, HttpServletRequest request, HttpServletResponse response) {
        try {
            username = new String(username.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        GenericResult genericResult = new GenericResult();
        switch (userType) {
            case IUserType.ADMIN: // 处理管理员登录
                logger.info("管理员 " + username + " 尝试登录");
                List<Admin> adminList = this.adminRepository.findByUsername(username);
                if(!this.userIsExist(adminList, genericResult)) { // 用户名不存在
                    logger.info("管理员 " + username + " 不存在");
                    break;
                }
                // 取用户信息
                Admin admin = adminList.get(0);
                if(!this.passwordIsCorrect(admin.getHashed_password(), password, genericResult)) { // 输入的密码不正确
                    logger.info("管理员 " + username + " 密码错误");
                    break;
                }
                logger.info("管理员 " + username + " 登录成功");
                // 登录成功
                admin.setHashed_password(null); // 清空密码信息
                this.loginSuccess(genericResult, admin, request);
                break;
            case IUserType.MARKET_MANAGER: // 处理商场管理员登录
                logger.info("商场管理员 " + username + " 尝试登录");
                List<MarketManager> marketManagerList = this.marketManagerRepository.findByUsername(username);
                if(!this.userIsExist(marketManagerList, genericResult)) { // 用户名不存在
                    logger.info("商场管理员 " + username + " 不存在");
                    break;
                }
                // 取商场管理员信息
                MarketManager marketManager = marketManagerList.get(0);
                if(!this.passwordIsCorrect(marketManager.getHashedPassword(), password, genericResult)) {
                    // 输入的密码不正确
                    logger.info("商场管理员 " + username + " 密码错误");
                    break;
                }
                // 登录成功
                marketManager.setHashedPassword(null); // 清空密码信息
                this.loginSuccess(genericResult, marketManager, request);
                break;
            case IUserType.MARKET_CASHIER: // 处理商场收银员登录
                logger.info("商场收银员 " + username + " 尝试登录");
                List<MarketCashier> marketCashierList = this.marketCashierRepository.findByUsername(username);
                if(!this.userIsExist(marketCashierList, genericResult)) { // 用户名不存在
                    logger.info("商场收银员 " + username + " 不存在");
                    break;
                }
                // 取商场s收银员信息
                MarketCashier marketCashier = marketCashierList.get(0);
                if(!this.passwordIsCorrect(marketCashier.getHashed_password(), password, genericResult)) {
                    // 输入的密码不正确
                    logger.info("商场收银员 " + username + " 密码错误");
                    break;
                }
                // 登录成功
                marketCashier.setHashed_password(null); // 清空密码信息
                this.loginSuccess(genericResult, marketCashier, request);
                break;
            case IUserType.BUSINESS: // 处理入住商家登录
                logger.info("入住商家 " + username + " 尝试登录");
                List<Business> businessList = this.businessRepository.findByUsername(username);
                if(!this.userIsExist(businessList, genericResult)) { // 用户名不存在
                    logger.info("入住商家 " + username + " 不存在");
                    break;
                }
                // 取用户信息
                Business business = businessList.get(0);
                if(!this.passwordIsCorrect(business.getHashedPassword(), password, genericResult)) { // 输入的密码不正确
                    logger.info("入住商家 " + username + " 密码错误");
                    break;
                }
                logger.info("入住商家 " + username + " 登录成功");
                // 登录成功
                business.setHashedPassword(null); // 清空密码信息
                this.loginSuccess(genericResult, business, request);
                break;
            default:

        }
        return genericResult;
    }

    /**
     * 判断用户名是否存在
     * @param userList 用户列表
     * @param result 响应结果
     * @return 存在用户名返回true，否则，返回false
     */
    private boolean userIsExist(List<?> userList, GenericResult result) {
        if(null == userList || userList.isEmpty()) {
            result.setData(null);
            result.setMessage("用户名不存在");
            result.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }
        return true;
    }

    /**
     * 判断密码是否正确
     * @param hashed_password 数据库中保存的密码
     * @param password 用户输入的密码
     * @param result 响应结果
     * @return 密码正确返回true，否则，返回false
     */
    private boolean passwordIsCorrect (String hashed_password, String password, GenericResult result) {
        try {
            if(!hashed_password.equals(DigestUtils.md5DigestAsHex(password.getBytes("utf-8")))) {
                result.setData(null);
                result.setMessage("输入密码错误");
                result.setStatus(HttpStatus.NOT_FOUND.value());
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 成功登录后把用户信息封装到响应结果中，把用户信息保存在session中
     * @param result 响应结果
     * @param data 响应结果包含的用户信息
     * @param request HttpServletRequest
     */
    private void loginSuccess(GenericResult result, Object data, HttpServletRequest request) {
        result.setStatus(HttpStatus.OK.value());
        result.setMessage("登录成功");
        result.setData(data); // 把登录成功的用户信息返回给客户端
        // 登录信息保存在session中
        request.getSession().setAttribute(ISessionKey.USER_INFO, data);

        // 记录登录人数 保存到Application 中
        Integer loginNumber = (Integer) request.getServletContext().getAttribute(this.LOGIN_NUMBER);
        loginNumber = (loginNumber == null ? 0 : loginNumber);
        request.getServletContext().setAttribute(this.LOGIN_NUMBER, loginNumber + 1);
    }

    /**
     * 通过token 获取用户信息
     *
     * @param token 登录成功返回的token
     * @return 用户信息
     */
    @Override
    public GenericResult getUserByToken(String token) {
        return null;
    }

    /**
     * 用户退出登录
     *
     * @param request HttpServletRequest
     * @return GenericResult
     */
    @Override
    public GenericResult logout(HttpServletRequest request) {
        request.getSession().setAttribute(ISessionKey.USER_INFO, null); // 清除登录信息
        // 记录登录人数 保存到Application 中
        Integer loginNumber = (Integer) request.getServletContext().getAttribute(this.LOGIN_NUMBER);
        loginNumber = (loginNumber == null ? 1 : loginNumber);
        request.getServletContext().setAttribute(this.LOGIN_NUMBER, loginNumber - 1); // 退出登录，在线人数减少1
        return GenericResult.ok(null);
    }

}
