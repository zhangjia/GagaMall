package io.zhangjia.mall.service;

import java.util.List;
import java.util.Map;

public interface AddressService {
    List<Map<String, Object>> getUserAddress(String userId);

    boolean addUserAddress(Map<String, Object> maps);

    Map<String, Object> getAddress4Edit(String addressId);

    int setDefaultAddress(String addressId, String userId);

    int deleteAddress(String addressId, String userId);


}
