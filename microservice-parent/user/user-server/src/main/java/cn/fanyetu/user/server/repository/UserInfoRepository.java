package cn.fanyetu.user.server.repository;

import cn.fanyetu.user.server.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhanghaonan
 * @date 2018/5/10
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
