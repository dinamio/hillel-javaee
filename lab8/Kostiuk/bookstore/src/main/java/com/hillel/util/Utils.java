package com.hillel.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Optional;

public class Utils {

    public static boolean isValidInput(String value) {
        return value != null && !value.isEmpty();
    }

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

    public static InputStream resourceAsStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static Optional<Long> requestID(HttpServletRequest req) throws IOException {
        return Optional.ofNullable(req.getParameter("id")).map(Long::valueOf);
    }

    public static boolean isNotEpmty(Collection collection) {
        return !collection.isEmpty();
    }

}
