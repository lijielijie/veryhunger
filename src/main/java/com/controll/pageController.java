package com.controll;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *接收文件上传
 * @author lijie
 */
@Controller
@RequestMapping("/page") 
public class pageController {
	@RequestMapping("/toAdit") 
	public String toAdit(HttpServletRequest req,String articleId) {
		req.getSession().setAttribute("editArticleId", "");
		if(articleId!=null||!"".equals(articleId)) {
			req.getSession().setAttribute("editArticleId", articleId);
			return "write";
		}
        return "err";
	}
	@RequestMapping("/toShow") 
	public String toShow(HttpServletRequest req,String articleId) {
		req.getSession().setAttribute("showArticleId", "");
		if(articleId!=null||!"".equals(articleId)) {
			req.getSession().setAttribute("showArticleId", articleId);
			return "showArticle";
		}
        return "err";
	}
	/**
	 * 跳转各网页
	 */
	@RequestMapping("/{html}") 
	public String gotoLable(HttpServletRequest req,@PathVariable("html") String html) {
		req.getSession().setAttribute("showArticleId", "");
		req.getSession().setAttribute("editArticleId", "");
        return html;
	}

}
