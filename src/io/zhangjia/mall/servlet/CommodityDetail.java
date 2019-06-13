package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/commodityDetail")
public class CommodityDetail extends HttpServlet {
    private CommodityService commodityService = new CommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commodityId = req.getParameter("commodityId");
        Commodity commodity = commodityService.queryCommodity(commodityId);
        System.out.println(commodity);
        req.setAttribute("commodity",commodity);
        req.getRequestDispatcher("/WEB-INF/views/proDetail.jsp").forward(req,resp);
    }
}
