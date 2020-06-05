package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.CarService;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.impl.CartServiceImpl;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/updateCount2CommodityDetail")
public class UpdateCount2CommodityDetailServlet extends HttpServlet {
    private CommodityService commodityService = new CommodityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId().toString();
        String SKUId = req.getParameter("SKUId");
        String count = req.getParameter("count");
        String val = req.getParameter("val");
        resp.setContentType("application/json;charset=utf-8");
        String action = req.getParameter("action");
        PrintWriter writer = resp.getWriter();
        System.out.println("aaaahahahah" + count + "--" + SKUId + "--" + userId + "val" + val);
        Map<String, Object> stringObjectMap = commodityService.updateCount2CommodityDetail(action, userId, SKUId, count, val);

        System.out.println(stringObjectMap);
        writer.println(JSON.toJSONString(stringObjectMap));
        writer.close();
    }
}
