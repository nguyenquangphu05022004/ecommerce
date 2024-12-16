package com.example.ecommerce.frame.common.servlet;

import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtils {


    public  static void writeJson(HttpServletResponse response, CommonResult<?> object) {
        String content = JsonUtils.write(object);
        write(response,object.getCode(), content, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        if(request == null) {
            return null;
        }
        return request.getRemoteAddr();
    }

    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        if(request != null) {
            return getUserAgent(request);
        }
        return null;
    }

    private static void write(HttpServletResponse response, int code, String content, String contentType) {
        response.setContentType(contentType);
        response.setStatus(code);
        try {
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
