package io.zhangjia.mall.servlet;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/cart/delete")
public class DeleteCartServlet extends HttpServlet {
    private CarService carService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId().toString();
        String[] skuIds = req.getParameterValues("SKUIds");

//        System.out.println(skuIds[0].getClass());
        List<String> list = new ArrayList<String>(Arrays.asList(skuIds));

        System.out.println(list.size() + " " + list + "lll " + req.getParameterValues("SKUId"));
        boolean b = carService.deleteCart(userId, list);
        System.out.println("skuids" + req.getParameter("SKUIds"));
        System.out.println("skuids" + req.getParameterValues("SKUIds"));
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":" + b + "}");
        writer.close();
    }
}
