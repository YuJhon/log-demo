package com.rain.trace.logtrace.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/***
 * Http 工具类
 */
public class HttpUtils {
    private HttpUtils() {
    }

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    private static final int MAX_LOG_CHAR = 1024;

    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

    public static String readRequest(HttpServletRequest request) {
        InputStream is = null;
        try {
            is = request.getInputStream();
            return getString(is);
        } catch (IOException e) {
            log.warn("split request body err", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return null;
    }

    private static String getString(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        byte[] b = new byte[MAX_LOG_CHAR];
        int n = -1;
        if ((n = is.read(b)) != -1) {
            sb.append(new String(b, 0, n));
            //请求内容超过1024字节时，只打印1024个字节
            if (n == MAX_LOG_CHAR) {
                sb.append("...more");
            }
        }
        return sb.toString();
    }

    public static String readResponse(InputStream is) {
        try {
            return getString(is);
        } catch (IOException e) {
            log.warn("split response body err", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return null;
    }

}
