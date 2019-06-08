package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.UserDao;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.utils.CommonDao;

import java.sql.Date;

public class UserDaoImpl extends CommonDao implements UserDao {

	@Override
	public User queryByUsernameAndPassword(String userName, String userPassword) {
		String sql = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";

		return query4Bean(sql,User.class,userName,userPassword);
	}

	@Override
	public int doInsert(User user) {
		String sql = "INSERT INTO users VALUES(seq_users.nextval,?,?,?,sysdate,?,?,?,?,?,?,0,1)";
		int i = executeUpdate(sql, user.getUserName(), user.getUserPassword(),user.getUserPayPassword(),
				user.getUserTel(),user.getUserMail(),user.getUserName(),user.getUserGender(),
				user.getUserBirthday(),user.getUserAvatar());
		return i ;
	}

	@Override
	public User queryByUsername(String userName) {
		String sql = "SELECT * FROM users WHERE user_name = ?";
		return query4Bean(sql,User.class,userName);
	}

	

}
