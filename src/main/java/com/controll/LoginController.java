package com.controll;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.base.BaseSet;
import com.entity.User;
import com.dao.LoginDao;

/**
 * 用户登录相关
 * @author lijie
 *
 */
@Controller
@RequestMapping("/")  
public class LoginController {
	@Resource  
    private LoginDao LoginDao;  
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
     * 首页
     */
    @RequestMapping("/")
    public String welcome(){ 
        return "welcome";
    }

    @RequestMapping("/head")
    public String head(){ 
        return "head";
    }
    @RequestMapping("/foot")
    public String foot(){ 
        return "foot";
    }
	/**
     * 登录
     */
    @RequestMapping("/user/login")
    public String login(){ 
        return "login";
    }
    /**
     * 校验
     */
    @RequestMapping("/user/checkLogin")
    @ResponseBody
    public int checkLogin(HttpServletRequest req,@RequestParam("name")String name,@RequestParam("pass")String pass){ 
    	HttpSession session=req.getSession();
        return checkLogin(session,name,pass);
    }
    /**
     * 登录
     */
    @RequestMapping("/user/loginIn")
    public String index(HttpServletRequest req,@RequestParam(value = "username", required = false)String name,@RequestParam(value ="password", required = false)String pass){ 
    	HttpSession session=req.getSession();
    	User user=(User)session.getAttribute(BaseSet.USER);
    	if (null!=user&&(boolean)session.getAttribute(BaseSet.LOGIN_FLAG)) {//会话有登录信息，返回成功
    		logger.info(user.getName()+"已经登陆  sessionId:"+session.getId());
    		return "welcome";
		}
    	if (1==checkLogin(req.getSession(),name,pass)) {
    		logger.info(name+"登录成功");
    		return "welcome";
		}
		return "login";
    }
    /**
     * 退出登录
     */
    @RequestMapping("/user/loginOut")
    public String loginOut(HttpServletRequest req, HttpServletResponse res){ 
        HttpSession s=req.getSession();
        User user=(User)s.getAttribute(BaseSet.USER);
        logger.info("loginOut:"+user.getName()+"退出登录");
        s.removeAttribute(BaseSet.USER);
        s.setAttribute(BaseSet.LOGIN_FLAG, false);
        s.invalidate();
        return "welcome";
    }
    /**
     * 获取用户名
     */
    @RequestMapping("/user/getUserName")
    @ResponseBody
    public String getUserName(HttpServletRequest req, HttpServletResponse res){ 
        HttpSession s=req.getSession();
        User user=(User)s.getAttribute(BaseSet.USER);
        if(user!=null) {
        	return user.getOwnname();
        }else{
        	return "";
        }
    }
    /**
     *校验登录信息
     */
    private int checkLogin(HttpSession session,String name,String pass){
        if (null==LoginDao.selectByName(name)) {
        	logger.error("login:没有此用户，请先注册");
        	return BaseSet.ERR_USER;
		}
        User user =LoginDao.checkPassword(name,pass);
        if (null==user) {
        	logger.error("login:密码错误");
        	return BaseSet.ERR_PASSWORD;
		}
        session.setAttribute(BaseSet.USER, user);
        session.setAttribute(BaseSet.LOGIN_FLAG, true);
        return BaseSet.SUCCESS_LOGIN;
    }
}
