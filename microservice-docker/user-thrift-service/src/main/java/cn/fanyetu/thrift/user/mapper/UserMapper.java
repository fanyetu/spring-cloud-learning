package cn.fanyetu.thrift.user.mapper;

import cn.fanyetu.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhanghaonan
 * @date 2018/8/29
 */
@Mapper
public interface UserMapper {

    @Select("select id, username, realName, mobile, email from pe_user where id = #{id,jdbcType=INTEGER}")
    UserInfo selectById(@Param("id") int id);

    @Select("select id, username, realName, mobile, email from pe_user where username = #{username,jdbcType=VARCHAR}")
    UserInfo selectByUsername(@Param("username") String username);

    @Insert("insert into pe_user (username, password, realName, mobile, email)" +
            "values (#{user.username}, #{user.password}, #{user.realName}, #{user.mobile}, #{user.email})")
    void insertUser(@Param("user") UserInfo user);
}
