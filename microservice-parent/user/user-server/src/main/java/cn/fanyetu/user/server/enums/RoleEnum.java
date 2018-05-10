package cn.fanyetu.user.server.enums;

import lombok.Getter;

/**
 * @author zhanghaonan
 * @date 2018/5/10
 */
@Getter
public enum  RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家");

    private Integer type;

    private String desc;

    RoleEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
