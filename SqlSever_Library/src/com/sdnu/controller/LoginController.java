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
			ADMIN admin = ADMIN.dao.findFirst("select * from ADMIN where [�û���]='" + name + "' and [����]='" + password + "'");
			boolean success = validateCaptcha("captcha");
			if (success) {
				if (admin != null) {
					//setSessionAttr("funcpermission", 10); Ȩ�޹���
					setSessionAttr("admin", admin);
					setSessionAttr("person","admin");
					redirect("/admin");
				} else {
					setAttr("msg", "�˺Ż����������");
					render("/Library/index.html");
				}
			} else {
				setAttr("msg", success ? "��ȷ" : "��֤�����");
				render("/Library/index.html");
			}
		} else {
			Reader reader = Reader.dao.findFirst("select * from Reader where [����֤��]='" + name + "' and [����]='" + password + "'");
			boolean success = validateCaptcha("captcha");
			if (success) {
				if (reader != null) {
					//setSessionAttr("funcpermission", 1); Ȩ�޹���
					setSessionAttr("reader", reader);
					setSessionAttr("person", "reader");
					redirect("/reader");
				} else {
					setAttr("msg", "�˺Ż����������");
					render("/Library/index.html");
				}
			} else {
				setAttr("msg", success ? "��ȷ" : "��֤�����");
				render("/Library/index.html");
			}
		}

	}
}
