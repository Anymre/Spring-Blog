package com.connext.spring_security.util;

/**
 * @Author: Marcus
 * @Date: 2018/12/24 9:17
 * @Version 1.0
 */
public class ReturnState {
    public static String returnState(boolean result){
        if (result == true) {
            return "{\"state\":true}";
        }
        return "{\"state\":false}";
    }
}
