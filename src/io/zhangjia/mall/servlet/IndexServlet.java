package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
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
import java.util.Map;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
//    private NavService navService = new NavServiceImpl();
private CommodityService commodityService = new CommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Commodity> commodities4Index = commodityService.getCommodities4Index();
//        req.getRequestDispatcher("proList.jsp").forward(req,resp);
        req.setAttribute("commodities4Index",commodities4Index);
        System.out.println("commodities4Index = " + JSON.toJSONString(commodities4Index));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
    }

}
