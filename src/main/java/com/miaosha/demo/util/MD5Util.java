package com.miaosha.demo.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt = "1a2b3c";

    public static String md5 (String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToForm(String inputPass){
        String src = salt + inputPass;
        return md5(src);
    }

    public static String formToDBPass(String inputPass,String salt){
        String src = salt + inputPass;
        return md5(src);
    }

    public static String inputPassToDBPass(String inputPass, String saltDB){
        return formToDBPass(inputPassToForm(inputPass),saltDB);
    }

}
