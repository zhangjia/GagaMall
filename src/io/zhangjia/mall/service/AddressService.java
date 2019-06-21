package io.zhangjia.mall.service;

import java.util.List;
import java.util.Map;

public interface AddressService {
    List<Map<String,Object>>  getUserAddress(String userId);
    boolean  addUserAddress(Map<String,Object> maps);


}
