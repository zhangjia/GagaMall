package io.zhangjia.mall.servlet;

import io.zhangjia.mall.service.DeliverGoodsService;
import io.zhangjia.mall.service.OrderService;
import io.zhangjia.mall.service.impl.DeliverGoodsServiceImpl;
import io.zhangjia.mall.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deliverGoods")
public class DeliverGoodsServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    private DeliverGoodsService deliverGoodsService = new DeliverGoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logistics = req.getParameter("logistics");
        String orderId = req.getParameter("orderId");

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        int i = deliverGoodsService.deliverGoods(orderId, logistics);
        System.out.println(" = 剪辑");
        writer.println("{\"success\":" + (i == 1) + "}");
        writer.close();

    }
}
