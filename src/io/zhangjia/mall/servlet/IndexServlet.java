package io.zhangjia.mall.servlet;

import io.zhangjia.mall.service.NavService;
import io.zhangjia.mall.service.impl.NavServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private NavService navService = new NavServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Map<String,Object>> firstNav = navService.getFirstNav();
        List<Map<String,Object>> secNav = navService.getSecNav();
        System.out.println(firstNav);
        System.out.println(secNav);
//        req.getSession().setAttribute("nav",nav);
//        req.getSession().setAttribute("nav2",1);
        req.setAttribute("firstNav",firstNav);
        req.setAttribute("secNav",secNav);
        req.setAttribute("nav2",1);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
//        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
