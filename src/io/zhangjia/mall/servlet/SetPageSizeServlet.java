package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.SiteSettingsService;
import io.zhangjia.mall.service.impl.SiteSettingsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setPageSize")
public class SetPageSizeServlet  extends HttpServlet {
    private SiteSettingsService siteSettingsService = new SiteSettingsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageSize = req.getParameter("pageSize");
        System.out.println("pageSize = " + pageSize);
        int result = siteSettingsService.editValue( "分页",pageSize);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(result == 1) {
            System.out.println(" 78787878= " );
            writer.println("{\"success\":"+true+"}");
        }

        writer.close();
    }
}
