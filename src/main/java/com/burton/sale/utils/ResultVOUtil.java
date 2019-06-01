package com.burton.sale.utils;

import com.burton.sale.vos.ResultVO;

/**
 * @author Burton
 * @title: ResultVOUtil
 * @projectName sale
 * @description: 返回给前端对象的工具类
 * @date 2019/5/28 19:33
 */
public class ResultVOUtil {
    /**
     * 成功 有数据
     * @param data
     * @return
     */
    public static  ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setResponseCode(0);
        resultVO.setResponseMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 成功 无数据
     * @return
     */
    public static  ResultVO success() {
        return success(null);
    }

    /**
     * 失败
     * @return
     */
    public static  ResultVO fail() {
        ResultVO resultVO = new ResultVO();
        resultVO.setResponseCode(1);
        resultVO.setResponseMsg("失败");
        resultVO.setData(null);
        return resultVO;
    }

}
