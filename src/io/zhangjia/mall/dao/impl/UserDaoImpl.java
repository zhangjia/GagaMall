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
	public int doInsert(String userName, String userPassword, String userPayPassword,
						String userTel, String userMail, String userGender, Date userBirthday, String userAvatar) {
		String sql = "INSERT INTO users VALUES(seq_users.nextval,?,?,?,sysdate,?,?,?,?,?,?,0,1)";
		int i = executeUpdate(sql, userName, userPassword,userPayPassword,userTel,userMail,userName,userGender,userBirthday,userAvatar);
		return i ;
	}

	@Override
	public User queryByUsername(String userName) {
		String sql = "SELECT * FROM users WHERE user_name = ?";
		return query4Bean(sql,User.class,userName);
	}

	

}
