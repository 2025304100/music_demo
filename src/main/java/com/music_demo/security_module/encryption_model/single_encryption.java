package com.music_demo.security_module.encryption_model;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @source 需要加密的字符串
 * @hashType 加密类型 （MD5 和 SHA-加密位数-1,256,512 (SHA=SHA-1）
 * @return
 */
public class single_encryption {

    public synchronized static String md5_key(String text, String type) throws NoSuchAlgorithmException {
        return getMd5(text, type);
    }

    private static String getMd5(String source, String type) throws NoSuchAlgorithmException {
        //1.获取MessageDigest对象
        MessageDigest digest = MessageDigest.getInstance(type);

        //2.执行加密操作
        byte[] bytes = source.getBytes();
        digest.update(bytes);
        //在MD5算法这，得到的目标字节数组的特点：长度固定为16
        byte[] targetBytes = digest.digest();

        //3.声明字符数组
        char[] characters = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        //4.遍历targetBytes
        StringBuilder builder = new StringBuilder();
        for (byte b : targetBytes) {
            //5.取出b的高四位的值
            //先把高四位通过右移操作拽到低四位
            int high = (b >> 4) & 15;

            //6.取出b的低四位的值
            int low = b & 15;

            //7.以high为下标从characters中取出对应的十六进制字符
            char highChar = characters[high];

            //8.以low为下标从characters中取出对应的十六进制字符
            char lowChar = characters[low];

            builder.append(highChar).append(lowChar);
        }

        return builder.toString();
    }


}
