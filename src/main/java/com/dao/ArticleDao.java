package com.dao;

import java.util.List;

import com.entity.Article;

public interface ArticleDao {
	int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    List<Article> getArticlesByUserId(String userId);
	List<Article> selfArticleList(String userId);
	public Article openArticle(String id);
    void updateArticle(Article article);
}