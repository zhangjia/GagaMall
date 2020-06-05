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
import java.util.List;
import java.util.Map;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
        User user = (User) session.getAttribute("user");
        System.out.println("user = " + user);
        String orderId = req.getParameter("orderId");
        System.out.println("orderId = " + orderId);
        Map<String, Object> order = orderService.getOrder(user.getUserId() + "", orderId);
        req.setAttribute("order", order);
        System.out.println("order = " + order);
        req.getRequestDispatcher("/WEB-INF/views/orderxq.jsp").forward(req, resp);
    }
}
