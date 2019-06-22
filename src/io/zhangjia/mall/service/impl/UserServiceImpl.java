package io.zhangjia.mall.service.impl;


import io.zhangjia.mall.dao.UserDao;
import io.zhangjia.mall.dao.impl.UserDaoImpl;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.UserService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public Map<String, Object> login(String userName, String userPassword) {
		User user = userDao.queryByUsername(userName);
		System.out.println("username = " + userName);
		System.out.println("username = " + userName.getClass());
		System.out.println("username = " + userPassword);
		System.out.println("username = " + userPassword.getClass());
		Map<String,Object> map = new HashMap<>();
		if(user != null) {

			if(user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword)) {
				map.put("user",user);
			} else {
				map.put("error","密码错误");
			}
		} else {
			map.put("error","用户名不存在");
		}
		return map;
	}

	@Override
	public Map<String, Object>register(User user) {
		User user2 = userDao.queryByUsername(user.getUserName());
		Map<String,Object> map = new HashMap<>();
		if(user2 == null) {
			int i = userDao.doInsert(user);
			if(i == 1) {
				map.put("user",user);
			} else {
				map.put("error","注册失败");
			}
		} else {
			map.put("error","用户名已存在");
		}

		return map;
	}



}
