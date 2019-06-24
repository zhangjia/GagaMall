package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.entity.FirstMenu;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.NavService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;
import io.zhangjia.mall.service.impl.NavServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private CommodityService commodityService = new CommodityServiceImpl();
    private NavService navService = new NavServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<FirstMenu> nav = navService.getNav();
//        req.setAttribute("nav",nav);


        String name = req.getParameter("name");


        String firstMenuId = req.getParameter("firstMenuId");
        String secMenuId = req.getParameter("secMenuId");

        String page = req.getParameter("page");

        String orders = req.getParameter("order");

        List<Commodity> commodities = commodityService.getCommodities(name,page,firstMenuId,secMenuId,orders);


        req.setAttribute("commodities",commodities);
        req.setAttribute("firstMenuChineseName",navService.getFirstMenuChineseName(secMenuId));

        req.setAttribute("commoditiesCount",commodityService.getPagesCount(firstMenuId,secMenuId,name));
//
        req.getRequestDispatcher("/WEB-INF/views/proList.jsp").forward(req,resp);
//        req.getRequestDispatcher("proList.jsp").forward(req,resp);
    }


}
