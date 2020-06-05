package io.zhangjia.mall.dao;

import java.util.List;

public interface AttributeKeyDao {
    int doInsert(List<String> lists);

    boolean queryAttributeKey(String key);
}
