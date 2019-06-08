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
	public int register(String userName, String userPassword, String userPayPassword, String userTel, String userMail, String userGender, Date userBirthday, String userAvatar) {
		int result = userDao.doInsert(userName,userPassword, userPayPassword,
				userTel, userMail, userGender, userBirthday, userAvatar);
		return result;
	}



}
