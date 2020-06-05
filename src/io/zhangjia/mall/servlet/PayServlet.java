package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.*;
import io.zhangjia.mall.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {
    private WalletService walletService = new WalletServiceImpl();
    private IOUService iouService = new IOUServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String payStyle = req.getParameter("payStyle");
        String payMoney = req.getParameter("payMoney");
        String orderId = req.getParameter("orderId");
        String paypassword = req.getParameter("paypassword");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> stringObjectMap = userService.judgePayPassword(user.getUserId() + "", paypassword);
        if (stringObjectMap.containsKey("error")) {
            result = stringObjectMap;
        } else {
            //        todo：bug，一直是上一个的session
            System.out.println("orderId11111111111111111111111111111 = " + orderId);
            String location = req.getParameter("location");


            if (("订单页").equals(location)) {
                System.out.println("heihei" + orderService.getOrder(user.getUserId() + "", orderId));
                System.out.println("orderService.getOrder(user.getUserId()+\"\",orderId).get(\"ORDERPRICE\") = " + orderService.getOrder(user.getUserId() + "", orderId).get("ORDERPRICE"));
                System.out.println("orderService.getOrder(user.getUserId()+\"\",orderId).get(\"ORDERPRICE\") = " + orderService.getOrder(user.getUserId() + "", orderId).get("ORDERPRICE").getClass());
                Double money = (Double) orderService.getOrder(user.getUserId() + "", orderId).get("ORDERPRICE");
                System.out.println("jiajiajiamoney = " + money);
                if (payStyle.equals("余额")) {
                    result = walletService.orderPayByBalance(user.getUserId() + "", money + "", orderId);
                }

                if (payStyle.equals("白条")) {
                    result = iouService.orderPayByIOU(user.getUserId() + "", money + "", orderId);
                }

            } else {

                Double money = (Double) orderService.getOrder(user.getUserId() + "", "" + session.getAttribute("orderId")).get("ORDERPRICE");
                System.out.println("直接下单页的orderId是 = " + session.getAttribute("orderId"));
                if (payStyle.equals("余额")) {
                    result = walletService.orderPayByBalance(user.getUserId() + "", money + "", "" + session.getAttribute("orderId"));
                }

                if (payStyle.equals("白条")) {
                    result = iouService.orderPayByIOU(user.getUserId() + "", money + "", "" + session.getAttribute("orderId"));
                }

            }

        }


        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(JSON.toJSONString(result));
        System.out.println(JSON.toJSONString(result));
        writer.close();

    }
}
