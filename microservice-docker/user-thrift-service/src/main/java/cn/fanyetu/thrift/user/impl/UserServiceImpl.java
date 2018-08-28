package cn.fanyetu.thrift.user.impl;

import cn.fanyetu.thrift.user.UserInfo;
import cn.fanyetu.thrift.user.UserService;
import org.apache.thrift.TException;

/**
 * 实现thrift生成的接口
 *
 * @author zhanghaonan
 * @date 2018/8/28
 */
public class UserServiceImpl implements UserService.Iface {

    @Override
    public UserInfo getUserById(int id) throws TException {
        return null;
    }

    @Override
    public UserInfo getUserByUsername(String username) throws TException {
        return null;
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {

    }
}
