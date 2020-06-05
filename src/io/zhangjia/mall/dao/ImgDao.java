package io.zhangjia.mall.dao;

import java.util.List;

public interface ImgDao {
    int doInsert(String img, Integer belong, Integer order, String type);
}
