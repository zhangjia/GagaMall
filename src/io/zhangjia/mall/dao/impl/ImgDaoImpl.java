package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.ImgDao;
import io.zhangjia.mall.utils.CommonDao;

public class ImgDaoImpl extends CommonDao implements ImgDao {

    @Override
    public int doInsert(String img, Integer belong, Integer order, String type) {
        String sql = "INSERT INTO img VALUES(seq_img.nextval,?,?,?,?,1)";
        return executeUpdate(sql, img, belong, order, type);
    }
}
