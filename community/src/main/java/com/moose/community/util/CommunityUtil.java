package com.moose.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");    //把生成随机字符串的横线使用空字符串替换

    }

    //MD5加密
    public static String md5(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }else {
            return DigestUtils.md5DigestAsHex(key.getBytes());
        }
    }



}
