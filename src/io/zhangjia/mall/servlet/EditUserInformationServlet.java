package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
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

@WebServlet("/editUserInformation")
public class EditUserInformationServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("user3333 = " + user);
        String nickname = req.getParameter("nickname");
        user.setUserNick(nickname);
        String gender =  req.getParameter("gender");
        user.setUserGender(gender);
        String date = req.getParameter("birthday");
        user.setDate(date);
//        String tel = req.getParameter("tel");
//        user.setUserTel(tel);
//        String mail = req.getParameter("mail");
//        user.setUserMail(mail);
//        String password = req.getParameter("password");
//        user.setUserPassword(password);
//        String paypassword = req.getParameter("paypassword");
//        user.setUserPayPassword(paypassword);
        int i = userService.editUserInformation(user);
        System.out.println("iiii = " + i);
        System.out.println("user2222 = " + user);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+(i==1)+"}");
        writer.close();
    }
}
