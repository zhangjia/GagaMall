package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.service.UserService;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入了doPost");
        //获取用户名和密码
        String username = req.getParameter("account");
        String password = req.getParameter("userPassword");
        String uri = req.getParameter("uri");
        Map<String, Object> map = userService.login(username, password);
        Map<String,Object> json = new HashMap<>();
        if(map.containsKey("user")){
            //登录成功，将用户信息存入session
            req.getSession().setAttribute("user",map.get("user"));
            json.put("result",true);
            //不过不是直接登录，将原地址存入uri
            if(uri != null){
                json.put("uri",uri);
            }
        }else{
            Object error = map.get("error");
            json.put("result",false);
            json.put("error",error);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(JSON.toJSONString(json));
        writer.close();
    }

}
