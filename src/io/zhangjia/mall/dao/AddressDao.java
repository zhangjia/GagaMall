package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface AddressDao {
    List<Map<String, Object>> queryByUserId(Integer userId);

    int doInsert(Map<String, Object> address);

    Map<String, Object> queryById(Integer addressId);

    int updateAddressStatus(String status, Integer addressId, Integer userId);

    int deleteDefaultAddress(Integer userId);
}
