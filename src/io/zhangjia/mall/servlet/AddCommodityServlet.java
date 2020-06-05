package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.service.AddCommodityService;
import io.zhangjia.mall.service.impl.AddCommodityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/addCommodity")
public class AddCommodityServlet extends HttpServlet {
    AddCommodityService addCommodityService = new AddCommodityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Object> list = new ArrayList<>();
        String commodityName = req.getParameter("commodityName");

        String firstMenuId = req.getParameter("firstMenuId");
        String sectMenuId = req.getParameter("secMenuId");
//        String[] spts = req.getParameterValues("spt");
//        String[] spxqts = req.getParameterValues("spxqt");
        String jsonAttr = req.getParameter("jsonAttr");
        String skuRecords = req.getParameter("skuRecords");
        Map<String, Object> maps = new HashMap<>();
        maps.put("firstMenuId", firstMenuId);
        maps.put("sectMenuId", sectMenuId);
        maps.put("commodityName", commodityName);
        maps.put("spt", req.getParameter("spt"));
        maps.put("spxqt", req.getParameter("spxqt"));
        maps.put("jsonAttr", jsonAttr);
        maps.put("skuRecords", skuRecords);

        list.add(firstMenuId);
        list.add(commodityName);
        list.add(req.getParameter("spt"));
        list.add(req.getParameter("spxqt"));
        list.add(jsonAttr);
        list.add(skuRecords);
        list.add(sectMenuId);
        System.out.println("进入了addCOm噢地啊是打发斯蒂芬’阿斯蒂芬阿斯蒂芬");


        addCommodityService.addCommodity(maps);

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();


        writer.println(JSON.toJSONString(list));
        System.out.println(list);
        writer.close();

    }

}
