package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.OrderService;
import io.zhangjia.mall.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
        User user = (User)session.getAttribute("user");
        String userId = user.getUserId() + "";
        String[] SKUIds =  req.getParameterValues("SKUIds");
        System.out.println("submitSKUIds = " + SKUIds);
        String addressId = req.getParameter("addressId");
        System.out.println("addressIdsubmit = " + addressId);
        boolean submit = orderService.submit(userId,addressId,"10","白条支付","备注",SKUIds);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+submit+"}");
        writer.close();
    }
}
