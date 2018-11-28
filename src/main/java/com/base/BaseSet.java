package com.base;

import java.text.SimpleDateFormat;

public class BaseSet {
	//会话key
	public static String USER="user";
	public static String LOGIN_FLAG="loginFlag";
	public static String COOKIE="cookie";
	public static String TOOKEN="tooken";

	//登录状态
	public static int ALREADY_LOGIN=-1;
	public static int NO_LOGIN=0;
	public static int SUCCESS_LOGIN=1;
	public static int ERR_USER=2;
	public static int ERR_PASSWORD=3;
	
	//工具项
	public static SimpleDateFormat fastDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	
	//配置项
	//public static String ARTICLE_IMAGEPATH="C:/Users/lijie/Desktop/image";
	public static String ARTICLE_IMAGEPATH="/app/fileStore/articleImage";
}
