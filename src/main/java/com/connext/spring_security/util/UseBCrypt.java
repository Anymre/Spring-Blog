package com.connext.spring_security.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Scanner;

/*
 * BCrypt Java实现
 * BCrypt也是一种哈希算法，相比MD5更安全，速度慢
 * */
public class UseBCrypt {
    public static String Encoder(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
