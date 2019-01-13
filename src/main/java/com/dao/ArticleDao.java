package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Article;

public interface ArticleDao {
	int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    List<Article> getArticlesByUserId(@Param("userId")String userId);
	List<Article> selfArticleList(@Param("userId")String userId);
	public Article openArticle(String id);
    void updateArticle(Article article);
}