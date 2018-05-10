package cn.fanyetu.user.server.service.impl;

import cn.fanyetu.user.server.dataobject.UserInfo;
import cn.fanyetu.user.server.repository.UserInfoRepository;
import cn.fanyetu.user.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanghaonan
 * @date 2018/5/10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 通过openid查询用户信息
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
