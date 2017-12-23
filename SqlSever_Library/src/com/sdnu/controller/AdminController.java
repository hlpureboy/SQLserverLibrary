package com.sdnu.controller;

import org.json.JSONObject;

import com.jfinal.core.Controller;
import com.sdnu.common.model.ADMIN;

public class AdminController extends Controller{
	public void index() {
		ADMIN admin = getSessionAttr("admin");
		setAttr("name",admin.get”√ªß√˚());
		render("/Library/admin.html");
	}
	
	public void changeAdmin() {
		JSONObject js = new JSONObject();
		String pass1 = getPara("pwd1");
		String pass2 = getPara("pwd2");
		System.out.println(pass1);
		System.out.println(pass2);
		if(pass1.equals(pass2)){
			ADMIN admin = getSessionAttr("admin");
			admin.set√‹¬Î(pass1);
			admin.update();
			js.put("status", "success");
			renderJson(js.toString());
		}else{
			js.put("status", "default");
			renderJson(js.toString());
		}
	}
}
