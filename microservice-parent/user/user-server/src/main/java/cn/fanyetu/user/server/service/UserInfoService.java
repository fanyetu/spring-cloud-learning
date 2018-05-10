package cn.fanyetu.user.server.service;

import cn.fanyetu.user.server.dataobject.UserInfo;

/**
 * @author zhanghaonan
 * @date 2018/5/10
 */
public interface UserInfoService {


    UserInfo findByOpenid(String openid);
}
