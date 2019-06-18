package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.AttributeValueDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class AttributeValueDaoImpl extends CommonDao implements AttributeValueDao {
    @Override
    public int doInsert(Map<Integer,String> maps) {

        return -1;
    }

    @Override
    public boolean queryAttributeValue(String value,String attributeKeyId) {
        String sql = "SELECT * FROM ATTRIBUTE_VALUE av WHERE av.ATTRIBUTE_VALUE_NAME = ? AND av.ATTRIBUTE_KEY_ID = ?";
        Map<String, Object> stringObjectMap = query4Map(sql, value,attributeKeyId);
        //如果是null，说明不存在
        return stringObjectMap == null;
    }
}
