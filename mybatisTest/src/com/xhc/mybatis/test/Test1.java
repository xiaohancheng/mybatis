package com.xhc.mybatis.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.xhc.mybatis.model.User;
import com.xhc.mybatis.service.UserService;

public class Test1 {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSessionFactory() {
		return sqlSessionFactory;
	}

	@Test
	public void testSelectUser() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			User user = (User) sqlSession.selectOne("com.xhc.mybatis.models.UserMapper.selectUserByID", 1);
			System.out.println(user.getUserAddress());
			System.out.println(user.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUser1() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			// User
			// user=(User)sqlSession.selectOne("com.xhc.mybatis.models.UserMapper.selectUserByID",1);
			UserService userService = sqlSession.getMapper(UserService.class);
			User user = userService.selectUserByID(1);
			System.out.println(user.getUserAddress());
			System.out.println(user.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUsers() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			List<User> users = userService.selectUsers("%sum%");
			for (User user : users) {
				System.out.println(user.getUserAddress() + "///" + user.getUserName());
			}
		} finally {
			sqlSession.close();
		}
	}
	@Test
	public void testAddUser() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			User user=new User();
			user.setUserAddress("guangdong,shaoguan");
			user.setUserAge(23);
			user.setUserName("xiaohancheng");
			userService.addUser(user);
			sqlSession.commit();//不提交不能保存
		} finally {
			sqlSession.close();
		}
	}
	@Test
	public void testdelUserById() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			userService.delUserById(6);
			sqlSession.commit();//不提交不能删除
		} finally {
			sqlSession.close();
		}
	}
	@Test
	public void testUpdateUser() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			UserService userService = sqlSession.getMapper(UserService.class);
			User user=userService.selectUserByID(1);
			user.setUserAge(99);
			userService.updateUser(user);
			sqlSession.commit();//不提交不能更新
		} finally {
			sqlSession.close();
		}
	}
}
