package com.xhc.mybatis.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.xhc.mybatis.model.Article;
import com.xhc.mybatis.service.ArticleService;

public class Test2 {
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
	public void testSelectArticle() {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			ArticleService articleService=sqlSession.getMapper(ArticleService.class);
			Article article=articleService.selectArticle(1);
			System.out.println(article.getTitle()+"//"+article.getContent()+"//"+article.getUser().getUserName());
		} finally {
			sqlSession.close();
		}
	}
}
