package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.api.mailcode.MailCode;
import io.zhangjia.mall.service.MailCodeService;
import io.zhangjia.mall.service.PhoneCodeService;
import io.zhangjia.mall.service.impl.MailCodeServiceImpl;
import io.zhangjia.mall.service.impl.PhoneCodeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getEmailCode")
public class getEmailCodeServlet extends HttpServlet {
    private MailCodeService mailCodeService = new MailCodeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("type");
        System.out.println("mail = " + mail);
        String mailCode = mailCodeService.getMailCode(mail);
        req.getSession().setAttribute("mailCode", mailCode);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        System.out.println("JSON.toJSONString(phoneCode)" + JSON.toJSONString(mailCode));
//        writer.println(JSON.toJSONString(phoneCode));
        boolean result = mailCode != null;
        writer.println("{\"success\":" + result + "}");
        writer.close();
    }
}
