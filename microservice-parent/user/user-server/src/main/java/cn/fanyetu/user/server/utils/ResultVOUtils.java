package cn.fanyetu.user.server.utils;

import cn.fanyetu.user.server.enums.ResultEnum;
import cn.fanyetu.user.server.vo.ResultVO;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public class ResultVOUtils {

    public static ResultVO error(ResultEnum result){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(result.getCode());
        resultVO.setMessage(result.getMessage());
        return resultVO;
    }

    public static <T> ResultVO<T> success(T data){
        ResultVO<T> result = new ResultVO<>();
        result.setCode(0);
        result.setMessage("成功");
        return result;
    }
}
