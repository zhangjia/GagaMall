package io.zhangjia.mall.insert;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.utils.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class InsertImgServlet extends HttpServlet {
    InsertImg i = new InsertImg();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        int cid = Integer.parseInt(req.getParameter("cid"));
        int order = Integer.parseInt(req.getParameter("order"));
        int i = 0;
        if (req.getParameter("img").equals("spt")) {
            System.out.println("商品图");

            i = this.i.insertCommodityImg(url, cid, order);
        } else {
            System.out.println("商品详情图");
            i = this.i.insertCommodityDetailImg(url, cid, order);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("添加结果：" + (i == 1));
        writer.close();
    }
}
