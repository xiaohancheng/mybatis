package com.xhc.mybatis.service;

import java.util.List;

import com.xhc.mybatis.model.User;

public interface UserService {
	public User selectUserByID(int id);

	public List<User> selectUsers(String userName);

	public void addUser(User user);

	public void delUserById(int id);

	public void updateUser(User user);
}
