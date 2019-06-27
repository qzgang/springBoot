package com.hurbao.sso.common.util;

import com.hurbao.sso.common.util.secret.EncryptAES;
import org.apache.commons.lang3.StringUtils;

import java.security.Key;

public class AESCryptUtil {
    private static final String encoding = "UTF-8";

    /**
     * aes密钥
     */
    private static String AES_KEY = "9b2ea105638049a2a4d29b8ca659ddaf";


    private static Key DEFAULT_KEY;
    static{
        try {
            DEFAULT_KEY=EncryptAES.initSecretKey(AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println(decryptByKey(encryptByKey("111")));
    }

    /**
     * 加密
     */
    public static String encryptByKey(String inputContent) {
        try {
            byte[] val= EncryptAES.encrypt(inputContent.getBytes(encoding), DEFAULT_KEY);
            return StringUtil.parseByte2HexStr(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     */
    public static String decryptByKey(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            byte[] oraVal=EncryptAES.decrypt(StringUtil.parseHexStr2Byte(content), DEFAULT_KEY);
            return new String(oraVal, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
