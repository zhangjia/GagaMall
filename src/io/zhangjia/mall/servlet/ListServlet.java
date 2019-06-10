package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer firstMenuId = null;
        Integer secMenuId = null;
        if(req.getParameter("firstMenuId") != null ) {
            firstMenuId   = Integer.parseInt(req.getParameter("firstMenuId"));

        }
        if(req.getParameter("secMenuId") !=null){
            secMenuId = Integer.parseInt(req.getParameter("secMenuId"));
        }


        List<Commodity> commodities = commodityService.queryCommodities(name,firstMenuId,secMenuId);

        req.setAttribute("commodities",commodities);
        req.getRequestDispatcher("/WEB-INF/views/proList.jsp").forward(req,resp);
//        req.getRequestDispatcher("proList.jsp").forward(req,resp);
    }
}
