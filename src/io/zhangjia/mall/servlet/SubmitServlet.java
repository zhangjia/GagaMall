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
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId() + "";
        String[] SKUIds = req.getParameterValues("SKUIds");
        System.out.println("submitSKUIds = " + SKUIds);
        String addressId = req.getParameter("addressId");
        System.out.println("addressIdsubmit = " + addressId);
//         运费先默认0元,去service中根据价格判断
        String note = req.getParameter("note");
        System.out.println("note = " + note);
        int submit = orderService.submit(userId, addressId, "0", "未支付", note, SKUIds);
        System.out.println("插入的订单记录ID是 = " + submit);
//        向session中插入订单Id
        session.setAttribute("orderId", submit);

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":" + (submit != 0) + "}");
        writer.close();
    }
}
