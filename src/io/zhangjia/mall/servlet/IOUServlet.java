package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.IOUService;
import io.zhangjia.mall.service.impl.IOUServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/iou")
public class IOUServlet extends HttpServlet {
    private IOUService iouService = new IOUServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Map<String, Object> stringObjectMap = iouService.queryUserIOU(user.getUserId() + "");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(stringObjectMap != null){
            stringObjectMap.put("success",true);
        writer.println(JSON.toJSONString(stringObjectMap));
        }

        System.out.println(JSON.toJSONString(stringObjectMap));
        writer.close();
    }
}
