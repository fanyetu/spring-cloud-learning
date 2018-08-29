package cn.fanyetu.thrift.user.impl;

import cn.fanyetu.thrift.user.UserInfo;
import cn.fanyetu.thrift.user.UserService;
import cn.fanyetu.thrift.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现thrift生成的接口
 *
 * @author zhanghaonan
 * @date 2018/8/28
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.selectById(id);
    }

    @Override
    public UserInfo getUserByUsername(String username) throws TException {
        return userMapper.selectByUsername(username);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.insertUser(userInfo);
    }
}
