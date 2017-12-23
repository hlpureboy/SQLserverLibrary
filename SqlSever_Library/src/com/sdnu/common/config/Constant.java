package com.sdnu.common.config;

import java.io.File;

import com.jfinal.kit.PathKit;

public class Constant {
	/**
	 * 定义当前服务器环境是否为开发模式 默认是
	 */
	public static boolean devMode=true;
	/**
	 * url参数分隔符
	 */
	public static final String URLPARASEPARATOR="-";
	/**
	 * view层根目录
	 */
	public static final String baseViewPath = "/WEB-INF/view/";
	/**
	 * 无权访问地址
	 */
	public static final String noAuthorityPagePath = "/WEB-INF/view/error/noauthority.html";
	/**
	 * 404页面地址
	 */
	public static final String error404PagePath = "/WEB-INF/view/error/404.html";
	/**
	 * 上传文件路径
	 */
	public static final String uploadSaveDir=PathKit.getWebRootPath() +File.separator+"upload";
	/**
	 * 定义用户sessionkey
	 */
	public static final String USER_SESSION_KEY="user";
	/**
	 * 最大上传尺寸
	 */
	public static final Integer MAXPOSTSIZE=1024*1024*5;
}
