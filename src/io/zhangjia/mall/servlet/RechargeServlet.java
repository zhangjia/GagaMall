package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.WalletService;
import io.zhangjia.mall.service.impl.WalletServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/recharge")
public class RechargeServlet extends HttpServlet {
    private WalletService walletService = new WalletServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String money = req.getParameter("money");
        System.out.println("money = " + money);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int recharge = walletService.recharge((user.getUserId() + ""), money);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":" + (recharge == 1) + "}");
        writer.close();
    }
}
