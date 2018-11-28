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
	/**
	 * 跳转各网页
	 */
	@RequestMapping("/{html}") 
	public String gotoLable(HttpServletRequest req,@PathVariable("html") String html) {
		System.out.println(html);
        return html;
	}

}
