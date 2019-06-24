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
import java.util.List;
import java.util.Map;

@WebServlet("/admin")
public class BackStageServlet extends HttpServlet {
    CarService carService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
        User user = (User)session.getAttribute("user");
        System.out.println(session.getAttribute("user"));
        if(user.getUserStatus() != 2) {
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
        } else {

        req.getRequestDispatcher("/WEB-INF/views/back-stage.jsp").forward(req,resp);
        }
    }
}
