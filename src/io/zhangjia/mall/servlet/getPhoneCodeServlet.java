package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.service.PhoneCodeService;
import io.zhangjia.mall.service.impl.PhoneCodeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getPhoneCode")
public class getPhoneCodeServlet extends HttpServlet {
    private PhoneCodeService phoneCodeService = new PhoneCodeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tel = req.getParameter("type");
        String phoneCode = phoneCodeService.getPhoneCode(tel);
        req.getSession().setAttribute("phoneCode", phoneCode);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        System.out.println("JSON.toJSONString(phoneCode)" + JSON.toJSONString(phoneCode));
//        writer.println(JSON.toJSONString(phoneCode));
        boolean result = phoneCode != null;
        writer.println("{\"success\":" + result + "}");
        writer.close();
    }
}
