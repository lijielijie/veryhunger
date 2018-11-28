package com.controll;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.base.BaseSet;
import com.dao.ArticleDao;
import com.entity.Article;
import com.entity.User;
/**
 * 用户登录相关
 * @author lijie
 *
 */
@Controller
@RequestMapping("/article")  
public class ArticleController {
	@Resource  
    private ArticleDao articleDao;  
	/**
	 * 文章内容入库
	 */
	@RequestMapping(value = "/uploadArticle", method = RequestMethod.POST)
	@ResponseBody
	public String uploadArticle(HttpServletRequest req, @RequestParam("articleRole") String articleRole,@RequestParam("articleCentent") String articleCentent,@RequestParam("title") String title) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		Article article=new Article();
		article.setId(String.valueOf(System.currentTimeMillis()));//毫秒数作为主键
		article.setUserId(user.getUid());
		article.setTitle(title);
		article.setRole(articleRole);
		article.setCentent(articleCentent);
		article.setState("1");
		article.setCreateTime(BaseSet.fastDateFormat.format(new Date()));
		article.setUpdateTime(BaseSet.fastDateFormat.format(new Date()));
		article.setReadCount(0);
		articleDao.insert(article);
		return "保存成功";
	}
	/**
	 * 获取用户可以访问的所有文章
	 */
	@RequestMapping(value = "/getArticleList")
	@ResponseBody
	public List<Article> getArticleList(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		return articleDao.getArticlesByUserId(user.getUid());
	}
	
	/**
	 * 获取用户自己的所有文章
	 */
	@RequestMapping(value = "/selfArticleList")
	@ResponseBody
	public List<Article> selfArticleList(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		return articleDao.selfArticleList(user.getUid());
	}
	/**
	 * 展示文章
	 */
	@RequestMapping(value = "/openArticle")
	@ResponseBody
	public ModelAndView openArticle(HttpServletRequest req,@RequestParam("id") String articleId) {
		ModelAndView m=new ModelAndView("articleList");
		Article article=articleDao.openArticle(articleId);
		article.setReadCount(article.getReadCount()+1);
		articleDao.updateByPrimaryKeySelective(article);//阅读数加一
		m.addObject("article", article);
		return m;
	}
}
