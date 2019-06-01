package com.burton.sale.utils;

import com.burton.sale.enums.ResultEnum;
import com.burton.sale.exceptions.SaleException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Burton
 * @location：com.burton.sale.utils.ExceptionUtil
 * @title: ExceptionUtil
 * @projectName sale
 * @description: 错误工具类
 * @date 2019/6/1 16:17
 */
public class ExceptionUtil {
    //定义在类中利用其预编译
    private static final Pattern pattern = Pattern.compile("[{].*?[}]");

    public static SaleException createException(String errorCode, Object...args) {
        //得到错误描述信息
        ResultEnum resultEnum = ResultEnum.find(errorCode);
        String message = resultEnum.getMessage();
        Matcher matcher = pattern.matcher(message);
        StringBuilder outputMsg = new StringBuilder();
        int start = 0;
        //for循环巩固 第一次只执行中间（判断） 后面每一次都先执行第三个，在执行中间这个（判断）
//        matcher.find(start)返回布尔值 为真进入循环体
        for (int index = 0; matcher.find(start); start = matcher.end()) {
            outputMsg.append(message.substring(start, matcher.start()));
            outputMsg.append("【");
            outputMsg.append(args[index++]);
            outputMsg.append("】");
        }
        outputMsg.append(message.substring(start, message.length()));
        message = outputMsg.toString();
        return new SaleException(ResultEnum.PARAMS_ERROR, message);

    }
}
