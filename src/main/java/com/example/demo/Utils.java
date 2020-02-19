package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Utils {


    private static ServletRequestAttributes getRequestContextHolder() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getRequestContextHolder().getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static void setDatabase(String stage) {
        getSession().setAttribute("database", stage);
    }

}
