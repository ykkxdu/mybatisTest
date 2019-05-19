package com.bid.ykk.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码生成
 */
public class PasswordHelper {
    //md5基础散列算法
    public  static  final String   ALGORITHM_NAME="md5";

    //自定义的散列次数
    public  static  final  Integer HASH_ITERATIONS=1024;

    public static String encryptPassword(String password) {
        //新密码
        String newPassword =new SimpleHash(ALGORITHM_NAME,password,
                null,HASH_ITERATIONS).toHex();
        //设置新密码
        return newPassword;
    }
}
