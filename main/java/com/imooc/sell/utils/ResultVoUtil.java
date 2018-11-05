package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVO;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/5 22:56
 * @Description:  返回结果格式
 */
public class ResultVoUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static  ResultVO fail(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }
}
