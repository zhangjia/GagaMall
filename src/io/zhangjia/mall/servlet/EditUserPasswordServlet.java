package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.UserService;
import io.zhangjia.mall.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/editUserPassword")
public class EditUserPasswordServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String newPassword  = req.getParameter("password");
        user.setUserPassword(newPassword);
        int i = userService.editUserInformation(user);
        if(i == 1){
            req.getSession().setAttribute("user",userService.getUserInformation(user.getUserName()));
        }

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+(i==1)+"}");
        writer.close();

    }
}
