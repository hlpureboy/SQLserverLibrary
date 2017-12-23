package com.sdnu.controller;


import org.json.JSONObject;

import com.jfinal.core.Controller;
import com.sdnu.common.model.Reader;

public class ReaderController extends Controller {
	public void index() {
		Reader reader = getSessionAttr("reader");
		setAttr("reader", reader);
		render("/Library/person.html");
	}
	public void changePwd() {
		JSONObject js = new JSONObject();
		Reader reader = getSessionAttr("reader");
		String old_passwd = reader.get密码();
		String new_passwd = getPara("new_passwd");
		String chk_passwd = getPara("chk_passwd");
		if(!old_passwd.equals(new_passwd)){
			if(chk_passwd.equals(new_passwd)){
				reader.set密码(chk_passwd);
				reader.update();
				js.put("status", "success");
				renderJson(js.toString());
			}else{
				js.put("status", "default");
				renderJson(js.toString());
			}
		}else{
			js.put("status", "default");
			renderJson(js.toString());
		}
	}
	public void changeMsg() {
		JSONObject js = new JSONObject();
		Reader reader = getSessionAttr("reader");
		String username = getPara("username");
		//String sex = getPara("sex");
		String obj = getPara("obj");
		String chat = getPara("chat");
		reader.set姓名(username);
		reader.set专业(obj);
		reader.set联系方式(chat);
		reader.update();
		js.put("status", "success");
		renderJson(js.toString());
	}
	public void addReader(){
		JSONObject js = new JSONObject();
		String numerid = getPara("numerid");
		String pwd = getPara("pwd");
		String sname = getPara("sname");
		Boolean sex = getParaToBoolean("sex");
		String brdata = getPara("brdata");
		String obj = getPara("obj");
		String chat = getPara("chat");
		Reader reader = getModel(Reader.class, "reader");
		reader.set借书证号(numerid);
		reader.set密码(pwd);
		reader.set姓名(sname);
		reader.set性别(sex);
		reader.set出生日期(brdata);
		reader.set专业(obj);
		reader.set联系方式(chat);
		reader.save();
		js.put("status", "success");
		renderJson(js.toString());
	}
	public void changReader() {
		JSONObject js = new JSONObject();
		String numerid1 = getPara("numerid1");
		String pwd1 = getPara("pwd1");
		String sname1 = getPara("sname1");
		Boolean sex1 = getParaToBoolean("sex1");
		String brdata1 = getPara("brdata1");
		String obj1 = getPara("obj1");
		String chat1 = getPara("chat1");
		Reader reader = Reader.dao.findFirst("select * from Reader where [借书证号]='" + numerid1 + "'");
		if(reader!=null){
			reader.set密码(pwd1);
			reader.set姓名(sname1);
			reader.set性别(sex1);
			reader.set出生日期(brdata1);
			reader.set专业(obj1);
			reader.set联系方式(chat1);
			reader.update();
			js.put("status", "success");
			renderJson(js.toString());
		}else{
			System.out.println("该用户不存在");
			js.put("status", "default");
			renderJson(js.toString());
		}
	}
	public void delReader() {
		JSONObject js = new JSONObject();
		String numerid2 = getPara("numerid2");
		Reader reader = Reader.dao.findFirst("select * from Reader where [借书证号]='" + numerid2 + "'");
		if(reader!=null){
			if(reader.get借书量()==0){
				reader.delete();
		        js.put("status", "success");
			    renderJson(js.toString());
			}else {
				js.put("status", "defalut1");
				renderJson(js.toString());
			}
		}else{
			js.put("status", "default2");
			renderJson(js.toString());
		}
		
	}
}
