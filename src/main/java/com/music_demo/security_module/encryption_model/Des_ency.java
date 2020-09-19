package com.music_demo.security_module.encryption_model;

import org.apache.shiro.codec.Base64;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Des_ency extends encry {
    //明文
    private static String TEXT;
    //密文
    private static String ps_PASS_TEXT;
    //密钥
    private Key key;
    //未加密字节
    private byte[] byte_MING = null;
    //加密字节
    private byte[] byte_MI = null;

    public static void main(String[] args) {
        Des_ency des_ency = new Des_ency();
        System.out.println(des_ency.symmetry_Encry(false, "我rg ", "12345678"));
        Des_ency des_ency1 = new Des_ency();
        System.out.println(des_ency1.decode_Encry(false, des_ency.symmetry_Encry(false, "我rg ", "12345678"), "12345678"));
    }

    //加密
    public synchronized String symmetry_Encry(boolean dds, String text, String key) {
        //初始上传数据
        TEXT = text;
        //判断运行是否正常

        Key_ds(key);
        if (dds) {

            setEncString();

        } else {
            SetEncode_Str();
        }
        return ps_PASS_TEXT;
    }

    //解密
    public synchronized String decode_Encry(boolean dds, String ps_text, String key) {
        this.ps_PASS_TEXT = ps_text;

        Key_ds(key);
        if (dds) {
            setDesString();

        } else {
            geDecode_str();
        }
        return ps_PASS_TEXT;
    }


    //根据字符生成密钥
    private boolean Key_ds(String key) {
        try {
            //字符串说明用那个密钥算法
            KeyGenerator gr = KeyGenerator.getInstance("DES");
            //制作密钥
            gr.init(new SecureRandom(key.getBytes()));
            //得到密钥
            this.key = gr.generateKey();
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    //base64+des混合加密
    private boolean setEncString() {
        if (TEXT == null) {
            return false;
        }
        try {
            this.byte_MING = this.TEXT.getBytes("UTF-8");
            this.byte_MI = getEncCode(this.byte_MING);
            //base编码
            this.byte_MI = Base64.encode(byte_MI);
            this.ps_PASS_TEXT = new String(byte_MI, "utf-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    //解密模块
    private byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteFina;
    }

    //纯des加密
    private boolean SetEncode_Str() {
        if (TEXT == null)
            return false;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            this.ps_PASS_TEXT = new String(cipher.doFinal(TEXT.getBytes()));
            return true;
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {

        } catch (NoSuchPaddingException e) {

        }
        return false;
    }

    // 解密:以String密文输入,String明文输出
    public void setDesString() {
        try {
            this.byte_MI = Base64.decode(ps_PASS_TEXT);
            this.byte_MING = this.getDesCode(byte_MI);
            this.TEXT = new String(byte_MING, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 解密以byte[]密文输入,以byte[]明文输出
    private byte[] getDesCode(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }

        return byteFina;
    }

    //纯des解密
    private boolean geDecode_str() {
        if (ps_PASS_TEXT == null)
            return false;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            this.TEXT = new String(cipher.doFinal(ps_PASS_TEXT.getBytes()), "utf-8");
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {

        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
