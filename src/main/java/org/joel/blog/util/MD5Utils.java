package org.joel.blog.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * MD5加密类
     * @param str 要加密的字符串
     * @return    加密后的字符
     */
    public static String code(String str){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md5.digest();
            int i;
            StringBuilder buffer = new StringBuilder("");
            for (byte aByte : bytes) {
                i = aByte;
                if (i < 0) i += 256;
                if (i < 16) buffer.append("0");
                buffer.append(Integer.toHexString(i));
            }
            // 32位加密
            return buffer.toString();
            // 16位的加密
            // return buffer.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("2021"));
    }
}
