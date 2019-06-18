package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface AttributeValueDao {
    int doInsert(Map<Integer,String>maps);
//    int doCommodityInsert(List<String> lists);
    boolean queryAttributeValue(String value,String attributeKeyId);
}
