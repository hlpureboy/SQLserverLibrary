package com.sdnu.common.config;

import java.io.File;

import com.jfinal.kit.PathKit;

public class Constant {
	/**
	 * ���嵱ǰ�����������Ƿ�Ϊ����ģʽ Ĭ����
	 */
	public static boolean devMode=true;
	/**
	 * url�����ָ���
	 */
	public static final String URLPARASEPARATOR="-";
	/**
	 * view���Ŀ¼
	 */
	public static final String baseViewPath = "/WEB-INF/view/";
	/**
	 * ��Ȩ���ʵ�ַ
	 */
	public static final String noAuthorityPagePath = "/WEB-INF/view/error/noauthority.html";
	/**
	 * 404ҳ���ַ
	 */
	public static final String error404PagePath = "/WEB-INF/view/error/404.html";
	/**
	 * �ϴ��ļ�·��
	 */
	public static final String uploadSaveDir=PathKit.getWebRootPath() +File.separator+"upload";
	/**
	 * �����û�sessionkey
	 */
	public static final String USER_SESSION_KEY="user";
	/**
	 * ����ϴ��ߴ�
	 */
	public static final Integer MAXPOSTSIZE=1024*1024*5;
}
