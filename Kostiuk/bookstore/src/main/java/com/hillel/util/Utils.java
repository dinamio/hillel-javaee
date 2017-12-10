package com.hillel.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Utils {

    public static byte[] resourceAsBytes(String path) {
        try {
            return IOUtils.toByteArray(resourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static String base64Encoded(byte[] bytes) {
        try {
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            return new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static InputStream resourceAsStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static String redirectTo(String path) {
        return "redirect:".concat(path);
    }


    public static String[] splitOnCommas(String string) {
        if (!StringUtils.isEmpty(string)) {
            return string.trim().split("[,]");
        }
        return new String[]{};
    }


}
