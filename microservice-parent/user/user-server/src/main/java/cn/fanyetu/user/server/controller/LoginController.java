package cn.fanyetu.user.server.controller;

import cn.fanyetu.user.server.constant.CookieConstant;
import cn.fanyetu.user.server.constant.RedisConstant;
import cn.fanyetu.user.server.dataobject.UserInfo;
import cn.fanyetu.user.server.enums.ResultEnum;
import cn.fanyetu.user.server.enums.RoleEnum;
import cn.fanyetu.user.server.service.UserInfoService;
import cn.fanyetu.user.server.utils.CookieUtils;
import cn.fanyetu.user.server.utils.ResultVOUtils;
import cn.fanyetu.user.server.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghaonan
 * @date 2018/5/10
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 买家登陆
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam String openid,
                          HttpServletResponse response){
        // openid 和数据库中的数据进行匹配
        UserInfo user = userInfoService.findByOpenid(openid);
        if (user == null){
            return ResultVOUtils.error(ResultEnum.LOGIN_FAILURE);
        }
        // 判断角色
        if (!RoleEnum.BUYER.getType().equals(user.getRole())){
            return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
        }

        // 设置cookie
        CookieUtils.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtils.success(null);
    }

    /**
     * 卖家登陆
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam String openid,
                           HttpServletResponse response,
                           HttpServletRequest request){
        // 判断是否已登陆
        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);
        if (cookie != null && StringUtils.isNotEmpty(redisTemplate.opsForValue()
                .get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            return ResultVOUtils.success(null);
        }


        // openid 和数据库中的数据进行匹配
        UserInfo user = userInfoService.findByOpenid(openid);
        if (user == null){
            return ResultVOUtils.error(ResultEnum.LOGIN_FAILURE);
        }
        // 判断角色
        if (!RoleEnum.SELLER.getType().equals(user.getRole())){
            return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
        }

        // redis 设置uuid
        String token = UUID.randomUUID().toString();
        Integer expire  = CookieConstant.expire;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                openid, expire, TimeUnit.SECONDS);

        // 设置cookie,token=uuid
        CookieUtils.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVOUtils.success(null);
    }
}
