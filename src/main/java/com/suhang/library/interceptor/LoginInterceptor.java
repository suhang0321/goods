package com.suhang.library.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.suhang.library.po.Admin;
import com.suhang.library.po.UserCustom;

/**
 * @author hang.su
 * @since 2017-12-12 17:20
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        if (url.indexOf("login")>=0||url.indexOf("regist")>=0||url.indexOf("main")>=0||url.indexOf("activation")>=0
            ||url.indexOf("book")>=0||url.indexOf("category")>=0||url.indexOf("captcha")>=0
            ||url.indexOf("adminjsps/login")>=0){
            return true;
        }
        HttpSession session = httpServletRequest.getSession();
        UserCustom userCustom = (UserCustom) session.getAttribute("sessionUser");
        Admin admin =(Admin) session.getAttribute("admin");
        if (userCustom!=null||admin!=null){
            return true;
        }
        /**
         * 程序走到这里，下边的部分都是拦截的部分，分两种情况分析，前台和后端
         */
        if (url.indexOf("admin")>=0){
            httpServletRequest.setAttribute("msg","您还没有登陆，请先登录后操作！");
            httpServletRequest.getRequestDispatcher("/adminjsps/login.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }else {
            httpServletRequest.setAttribute("msg","您还没有登陆，请先登录后操作！");
        httpServletRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpServletRequest, httpServletResponse);
        return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
