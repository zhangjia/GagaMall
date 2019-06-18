package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/commodityDetail")
public class CommodityDetailServlet extends HttpServlet {
    private CommodityService commodityService = new CommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commodityId = req.getParameter("commodityId");
        Commodity commodity = commodityService.getCommodity(commodityId);
        System.out.println(commodity);
        String SPEC = commodityService.getCommoditySPEC(commodityId);

        req.setAttribute("SPEC", JSON.parseObject(SPEC));
        System.out.println("SPEC" + JSON.parseObject(SPEC));
        req.setAttribute("commodity",commodity);
        req.getRequestDispatcher("/WEB-INF/views/proDetail.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("SPEC");
        List<Map<String, Object>> maps = commodityService.getCommoditySPU(json);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        writer.println(JSON.toJSONString(maps));
        writer.close();
    }
}
