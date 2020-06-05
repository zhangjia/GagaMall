package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.MailCodeService;
import io.zhangjia.mall.service.PhoneCodeService;
import io.zhangjia.mall.service.UserService;
import io.zhangjia.mall.service.impl.MailCodeServiceImpl;
import io.zhangjia.mall.service.impl.PhoneCodeServiceImpl;
import io.zhangjia.mall.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private PhoneCodeService phoneService = new PhoneCodeServiceImpl();
    private MailCodeService mailCodeService = new MailCodeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入了doPost");

        Map<String, Object> json = new HashMap<>();
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        System.out.println("action = " + req.getParameter("action"));
        User user = new User();
        String username = req.getParameter(req.getParameter("action"));
//        判断是不是手机注册
        user.setUserName(username);
        if (req.getParameter("action").equals("userTel")) {
            System.out.println("\"是手机号登录\" = " + "是手机号登录");
//            判断输入的验证码是否正确
            int phoneCode = phoneService.isPhoneCodeRight(req.getParameter("codes"), req.getSession());
            if (phoneCode == 0) {
                System.out.println("\"不正确\" = " + "是手机号登录");
                json.put("result", false);
                json.put("error", "验证码不正确");
                writer.println(JSON.toJSONString(json));
                writer.close();
                return;
            } else {
                user.setUserTel(username);
            }
        }

//        判断是不是邮箱注册
        if (req.getParameter("action").equals("userEmail")) {
            System.out.println("\"是Email登录\" = " + "是Email登录");
            //            判断输入的验证码是否正确
            int emailCodes = mailCodeService.isMailCodeRight(req.getParameter("codes"), req.getSession());
            if (emailCodes == 0) {
                System.out.println("\"不正确\" = " + "是Email录");
                json.put("result", false);
                json.put("error", "验证码不正确");
                writer.println(JSON.toJSONString(json));
                writer.close();
                return;
            } else {
                user.setUserMail(username);
            }
        }
        //获取用户名和密码
//        String username = req.getParameter(req.getParameter("action"));
        String password = req.getParameter("userPassword");
        user.setUserPassword(password);
        String uri = req.getParameter("uri");
//        User user = new User(username,password,null,null,null,null,null,null,null,null,null,null);
        Map<String, Object> map = userService.register(user);
        System.out.println("验证");
        /*如果注册成功*/
        if (map.containsKey("user")) {

            req.getSession().setAttribute("user", map.get("user"));
            json.put("result", true);

            if (uri != null) {
                json.put("uri", uri);
            }
        } else {
            Object error = map.get("error");
            json.put("result", false);
            json.put("error", error);
        }

        writer.println(JSON.toJSONString(json));
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
//        req.getRequestDispatcher("/WEB-INF/views/zhangjia.jsp").forward(req,resp);
    }

}
