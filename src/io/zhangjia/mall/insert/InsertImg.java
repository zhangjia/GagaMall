package io.zhangjia.mall.insert;

import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Scanner;

public class InsertImg extends CommonDao {
    public int insertCommodityImg(String url, int id, int order) {
        String sql = " INSERT INTO img VALUES(seq_img.nextval,?,?,?,'商品图',1)";
        return executeUpdate(sql, url, id, order);
    }

    public int insertCommodityDetailImg(String url, int id, int order) {
        String sql = " INSERT INTO img VALUES(seq_img.nextval,?,?,?,'商品详情图',1)";
        return executeUpdate(sql, url, id, order);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("请输入网址：");
        String url = input.nextLine();
        System.out.println("请输入所属商品ID:");
        int id = input.nextInt();
        System.out.println("请输入顺序");
        int order = input.nextInt();
//        System.out.println("插入结果：" + new InsertImg().insertCommodityImg(url,id,order));
        System.out.println("插入结果：" + new InsertImg().insertCommodityDetailImg(url, id, order));
    }
}
