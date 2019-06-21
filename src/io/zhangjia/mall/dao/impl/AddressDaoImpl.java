package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.AddressDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class AddressDaoImpl extends CommonDao implements AddressDao {

    /**
     * 根据用户的Id查询该用户的全部可用的地址
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql = "SELECT * FROM ADDRESS WHERE ADDRESS_STATUS != 0 AND USER_ID = ?";
        System.out.println(userId );
        System.out.println(query4MapList(sql,userId) );
        return  query4MapList(sql,userId);
    }

    @Override
    public int doInsert(Map<String, Object> address) {
       String sql = "INSERT INTO address VALUES(seq_address.nextval,?,?,?,?,?,sysdate,?)";
        System.out.println(address);
       return executeUpdate(sql,address.get("name"),address.get("userId"),
               address.get("postcode"),address.get("tel"),address.get("detailedAddress"),address.get("status"));
    }

    @Override
    public Map<String, Object> queryById(Integer addressId) {
        String sql = "SELECT * FROM ADDRESS WHERE ADDRESS_ID = ? AND ADDRESS_STATUS != 0";
        return query4Map(sql,addressId);
    }
}
