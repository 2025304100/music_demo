package com.music_demo.security_module.encryption_model;


import javax.crypto.Cipher;
import java.beans.Encoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSA_ency {
    //私钥
    private RSAPrivateKey privateKey;
    //公钥
    private RSAPublicKey publicKey;


    //加密处理
    private void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPaorGen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPaorGen.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    //加密器
    public String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        //byte[] decoded = Base64.decodeBase64(publicKey);
        byte[] decoded = Base64.decode(publicKey.getBytes("utf-8"));
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    //解密器
    public String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decode(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decode(privateKey.getBytes("utf-8"));
        //密钥解密器
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public static synchronized Map<Integer, String> Encrypt(String name) throws Exception {
        RSA_ency encry = new RSA_ency();
        encry.genKeyPair();
        Map<Integer, String> map = new HashMap<>();
        map.put(0, Base64.encodeToString(encry.publicKey.getEncoded()));
        map.put(1, Base64.encodeToString(encry.privateKey.getEncoded()));
        map.put(2, encry.encrypt("h1h", Base64.encodeToString(encry.publicKey.getEncoded())));
        return map;
    }

    public static synchronized String Decrypt(String pas, String privateKey) throws Exception {
        RSA_ency encry = new RSA_ency();
        encry.genKeyPair();

        return encry.decrypt(pas, privateKey);
    }


}
