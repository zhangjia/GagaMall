package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.AttributeKeyDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class AttributeKeyDaoImpl extends CommonDao implements AttributeKeyDao {
    @Override
    public int doInsert(List<String> lists) {
        for (String list: lists ) {
            if(queryAttributeKey(list)) {
                String sql = "INSERT INTO attribute_key VALUES(seq_attribute_key.nextval,?,1,sysdate,sysdate,1)";
                int i = executeUpdate(sql,list);
                return i;
            } else {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public boolean queryAttributeKey(String key) {
        String sql = "SELECT * FROM ATTRIBUTE_KEY WHERE ATTRIBUTE_KEY.ATTRIBUTE_KEY_NAME = ?";
        Map<String, Object> stringObjectMap = query4Map(sql, key);
        //如果是null，说明不存在
        return stringObjectMap == null;
    }
}
