package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.FirstMenu;
import io.zhangjia.mall.service.NavService;
import io.zhangjia.mall.service.impl.NavServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/saveOrUpdateCommodity")
public class SaveOrUpdateCommodity extends HttpServlet {
    private NavService navService = new NavServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FirstMenu> nav = navService.getNav();

        req.setAttribute("nav",nav);
        req.getRequestDispatcher("/WEB-INF/views/sou-commodity.jsp").forward(req,resp);
    }
}
