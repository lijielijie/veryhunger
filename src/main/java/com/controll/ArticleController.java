package com.controll;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.base.BaseSet;
import com.dao.ArticleDao;
import com.entity.Article;
import com.entity.User;

/**
 * 用户登录相关
 * 
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
	public String uploadArticle(HttpServletRequest req, @RequestParam("articleUuid") String articleUuid,
			@RequestParam("articleRole") String articleRole, @RequestParam("articleContent") String articleContent,
			@RequestParam("title") String title) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		
		if(StringUtils.isNotEmpty(articleUuid)){//更新时
			Article articleForCheck = articleDao.openArticle(articleUuid);
			if(!articleForCheck.getUserId().equals(user.getUid())) {//文章作者不是当前登录人
				return "errUser";
			}
		}
		Article article = new Article();
		article.setUserId(user.getUid());
		article.setTitle(title);
		article.setRole(articleRole);
		article.setContent(articleContent);
		article.setState("1");
		article.setUpdateTime(BaseSet.fastDateFormat.format(new Date()));
		article.setReadCount(0);

		if (StringUtils.isNotEmpty(articleUuid)) {// 不为空，则更新
			article.setId(articleUuid);
			articleDao.updateByPrimaryKeySelective(article);
		} else {
			article.setId( UUID.randomUUID().toString().replaceAll("\\-", ""));
			article.setCreateTime(BaseSet.fastDateFormat.format(new Date()));
			articleDao.insert(article);
		}
		return "success";
	}

	/**
	 * 获取用户可以访问的所有文章
	 */
	@RequestMapping(value = "/getArticleList")
	@ResponseBody
	public List<Article> getArticleList(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		return articleDao.getArticlesByUserId(user == null ? "" : user.getUid());
	}

	/**
	 * 获取用户自己的所有文章
	 */
	@RequestMapping(value = "/selfArticleList")
	@ResponseBody
	public List<Article> selfArticleList(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(BaseSet.USER);
		if (user == null) {
			return null;
		}
		return articleDao.selfArticleList(user.getUid());
	}

	/**
	 * 修改文章
	 */
	@RequestMapping(value = "/editArticle")
	@ResponseBody
	public Article readArticle(HttpServletRequest req, String articleId) {
		Article article = articleDao.openArticle(req.getSession().getAttribute("editArticleId").toString());
		return article;
	}

	/**
	 * 展示文章
	 */
	@RequestMapping(value = "/showArticle")
	@ResponseBody
	public Article showArticle(HttpServletRequest req) {
		Article article = articleDao.openArticle(req.getSession().getAttribute("showArticleId").toString());
		article.setReadCount(article.getReadCount() + 1);
		articleDao.updateByPrimaryKeySelective(article);// 阅读数加一
		return article;
	}
}
