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
import java.io.IOException;

@WebServlet("/personalInformation")
public class PersonalInformationServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        User userInformation = userService.getUserInformation(userId);
//        req.getSession().setAttribute("userInformation", userInformation);
//        System.out.println("userInformation = " + JSON.toJSONString(userInformation));
        System.out.println();
        System.out.println(" =ssss " + JSON.toJSONString(req.getSession().getAttribute("user")));
        req.getRequestDispatcher("/WEB-INF/views/mygrxx.jsp").forward(req, resp);
    }
}
