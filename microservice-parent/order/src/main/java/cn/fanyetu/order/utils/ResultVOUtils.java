package cn.fanyetu.order.utils;


import cn.fanyetu.order.viewobject.ResultVO;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public class ResultVOUtils {

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }
}
