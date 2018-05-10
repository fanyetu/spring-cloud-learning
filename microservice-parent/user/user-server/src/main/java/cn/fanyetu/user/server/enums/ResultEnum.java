package cn.fanyetu.user.server.enums;

import lombok.Getter;

/**
 * @author zhanghaonan
 * @date 2018/4/29
 */
@Getter
public enum ResultEnum {

    LOGIN_FAILURE(1, "登陆失败"),
    ROLE_ERROR(2, "角色权限有误");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
