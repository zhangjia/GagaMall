package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.CarService;
import io.zhangjia.mall.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/updateCount")
public class UpdateCountServlet extends HttpServlet {
    private CarService carService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String userId = user.getUserId().toString();
        String SKUId =  req.getParameter("SKUId");
        String count = req.getParameter("count");
        resp.setContentType("application/json;charset=utf-8");
        String action = req.getParameter("action");
        PrintWriter writer = resp.getWriter();

        Map<String, Object> stringObjectMap = carService.updateCount(action, userId, SKUId,count);
        System.out.println(stringObjectMap + "1.03");
        writer.println(JSON.toJSONString(stringObjectMap));
        writer.close();

    }
}
