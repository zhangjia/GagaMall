package io.zhangjia.mall.filter;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.utils.HttpFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/mygxin.jsp"})
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            String requestURI = request.getRequestURI();
            //获取请求参数（查询字符串）
            String queryString = request.getQueryString();
            System.out.println("queryString = " + queryString);
            System.out.println("requestURI = " + requestURI);

            if (requestURI.endsWith("delete") || requestURI.endsWith("saveOrUpdate")) {
//            if (isAjax(request)) {
                //返回json字符串
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> map = new HashMap<>();
                map.put("isLogin", false);
                writer.println(JSON.toJSONString(map));
                writer.close();

            } else {
                //没有登录，去登录
                if(queryString != null){
                    requestURI += ("?" + queryString);
                }
                response.sendRedirect(request.getContextPath() + "/login.jsp?uri=" + requestURI);
            }
        } else {
            //已经登录，放行
            filterChain.doFilter(request, response);
        }

    }



}
