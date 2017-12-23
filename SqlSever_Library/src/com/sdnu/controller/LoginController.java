package com.sdnu.controller;

import com.jfinal.core.Controller;
import com.sdnu.common.model.ADMIN;
import com.sdnu.common.model.Reader;

public class LoginController extends Controller {
	public void index() {
		render("/Library/index.html");
	}

	public void img() {
		renderCaptcha();
	}

	public void login() {

		String name = getPara("username");
		String password = getPara("password");
		String role = getPara("role");
		if (role.equals("admin")) {
			ADMIN admin = ADMIN.dao.findFirst("select * from ADMIN where [用户名]='" + name + "' and [密码]='" + password + "'");
			boolean success = validateCaptcha("captcha");
			if (success) {
				if (admin != null) {
					//setSessionAttr("funcpermission", 10); 权限管理
					setSessionAttr("admin", admin);
					setSessionAttr("person","admin");
					redirect("/admin");
				} else {
					setAttr("msg", "账号或者密码错误");
					render("/Library/index.html");
				}
			} else {
				setAttr("msg", success ? "正确" : "验证码错误");
				render("/Library/index.html");
			}
		} else {
			Reader reader = Reader.dao.findFirst("select * from Reader where [借书证号]='" + name + "' and [密码]='" + password + "'");
			boolean success = validateCaptcha("captcha");
			if (success) {
				if (reader != null) {
					//setSessionAttr("funcpermission", 1); 权限管理
					setSessionAttr("reader", reader);
					setSessionAttr("person", "reader");
					redirect("/reader");
				} else {
					setAttr("msg", "账号或者密码错误");
					render("/Library/index.html");
				}
			} else {
				setAttr("msg", success ? "正确" : "验证码错误");
				render("/Library/index.html");
			}
		}

	}
}
