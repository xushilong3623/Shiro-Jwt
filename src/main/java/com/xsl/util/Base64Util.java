package com.xsl.util;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;


public class Base64Util {



    private static final Logger logger = Logger.getLogger(Base64Util.class);

    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("[getBase64] err: ", e);
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                logger.error("[getFromBase64] err: ", e);
            }
        }
        return result;
    }

    public byte[] base64ToImage(String base64) {
        if (base64 == null) {
            return null;
        }
        byte[] bytes = Base64.decodeBase64(base64);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        return bytes;
    }

}
