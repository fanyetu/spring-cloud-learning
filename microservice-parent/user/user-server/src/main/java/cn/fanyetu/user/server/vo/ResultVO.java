package cn.fanyetu.user.server.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @author zhanghaonan
 * @date 2018/4/18
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 内容
     */
    private T data;
}
