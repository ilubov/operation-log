package com.i.lubov.util;

import cn.hutool.http.useragent.UserAgentUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.ByteStreams;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class HttpContextUtils {
    private static final LoadingCache<String, byte[]> regionCache = CacheBuilder
            .newBuilder().build(new CacheLoader<String, byte[]>() {
        public byte[] load(String s) throws Exception {
            InputStream inputStream = HttpContextUtils.class
                    .getClassLoader()
                    .getResourceAsStream("ip2region/ip2region.db");
            return ByteStreams.toByteArray(inputStream);
        }
    });

    public HttpContextUtils() {}

    private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getRequestIp() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException var3) {
            }
        }

        return ip;
    }

    public static String ip2Region(String ip) {
        try {
            DbSearcher searcher = new DbSearcher(new DbConfig(), (byte[])regionCache.get("region"));
            DataBlock dataBlock = searcher.memorySearch(ip);
            String address = dataBlock.getRegion().replace("0|", "");
            if (address.charAt(address.length() - 1) == '|') {
                address = address.substring(0, address.length() - 1);
            }

            return "内网IP|内网IP".equals(address) ? "内网IP" : address;
        } catch (Exception var4) {
            return null;
        }
    }

    public static String getBrowser() {
        HttpServletRequest request = getHttpServletRequest();
        return UserAgentUtil.parse(request.getHeader("User-Agent")).getBrowser().getName();
    }
}
