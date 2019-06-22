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
        String sql = "SELECT * FROM ADDRESS WHERE ADDRESS_STATUS != 0 AND USER_ID = ? ORDER BY ADDRESS_STATUS DESC";
        System.out.println(userId );
        System.out.println(query4MapList(sql,userId) );
        return  query4MapList(sql,userId);
    }

    @Override
    public int doInsert(Map<String, Object> address) {
       String sql = "INSERT INTO address VALUES(seq_address.nextval,?,?,?,?,?,sysdate,1,?,?,?,?)";
        System.out.println("szz" + address);

       return executeUpdate(sql,address.get("name"),address.get("userId"),
               address.get("postcode"),address.get("tel"),address.get("detailedAddress")
       ,address.get("province"),address.get("city"),address.get("district"),address.get("country"));
    }

    @Override
    public Map<String, Object> queryById(Integer addressId) {
        String sql = "SELECT * FROM ADDRESS WHERE ADDRESS_ID = ? AND ADDRESS_STATUS != 0";
        return query4Map(sql,addressId);
    }

    @Override
    public int updateAddressStatus(String status,Integer addressId,Integer userId) {
        String sql = "UPDATE ADDRESS SET ADDRESS_STATUS = ? WHERE ADDRESS_ID = ? AND USER_ID=?";
        return executeUpdate(sql,status,addressId,userId);
    }

    @Override
    public int deleteDefaultAddress(Integer userId) {
        String sql = "UPDATE ADDRESS SET ADDRESS_STATUS = 1 WHERE  ADDRESS_STATUS = 2 AND USER_ID = ?";
        System.out.println(userId+"suid");
        return executeUpdate(sql,userId);
    }
}
