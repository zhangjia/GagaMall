package io.zhangjia.mall.filter;

import io.zhangjia.mall.entity.FirstMenu;
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

@WebFilter(urlPatterns = { "/commodityDetail","/cart","/index","/list","/personal",
"/address","/myorder","/orderDetails","/personalInformation","/wallet","/admin","/settlement"})
public class NavFilter extends HttpFilter {
    private NavService navService = new NavServiceImpl();
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        List<FirstMenu> nav = navService.getNav();
        request.setAttribute("nav",nav);
        filterChain.doFilter(request,response);
    }
}
