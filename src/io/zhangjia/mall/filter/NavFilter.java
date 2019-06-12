package io.zhangjia.mall.filter;

import io.zhangjia.mall.service.NavService;
import io.zhangjia.mall.service.impl.NavServiceImpl;
import io.zhangjia.mall.utils.HttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/*
@WebFilter("/list")
public class NavFilter extends HttpFilter {
    private NavService navService = new NavServiceImpl();
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        List<Map<String,Object>> firstNav = navService.getFirstNav();
        List<Map<String,Object>> secNav = navService.getSecNav();
        System.out.println(firstNav);
        System.out.println(secNav);

        request.setAttribute("firstNav",firstNav);
        request.setAttribute("secNav",secNav);

        System.out.println(firstNav + "firstnav");
        filterChain.doFilter(request, response);

    }
}
*/
