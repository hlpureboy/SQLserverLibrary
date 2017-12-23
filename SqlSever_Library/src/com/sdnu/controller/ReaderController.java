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
		String old_passwd = reader.get����();
		String new_passwd = getPara("new_passwd");
		String chk_passwd = getPara("chk_passwd");
		if(!old_passwd.equals(new_passwd)){
			if(chk_passwd.equals(new_passwd)){
				reader.set����(chk_passwd);
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
		reader.set����(username);
		reader.setרҵ(obj);
		reader.set��ϵ��ʽ(chat);
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
		reader.set����֤��(numerid);
		reader.set����(pwd);
		reader.set����(sname);
		reader.set�Ա�(sex);
		reader.set��������(brdata);
		reader.setרҵ(obj);
		reader.set��ϵ��ʽ(chat);
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
		Reader reader = Reader.dao.findFirst("select * from Reader where [����֤��]='" + numerid1 + "'");
		if(reader!=null){
			reader.set����(pwd1);
			reader.set����(sname1);
			reader.set�Ա�(sex1);
			reader.set��������(brdata1);
			reader.setרҵ(obj1);
			reader.set��ϵ��ʽ(chat1);
			reader.update();
			js.put("status", "success");
			renderJson(js.toString());
		}else{
			System.out.println("���û�������");
			js.put("status", "default");
			renderJson(js.toString());
		}
	}
	public void delReader() {
		JSONObject js = new JSONObject();
		String numerid2 = getPara("numerid2");
		Reader reader = Reader.dao.findFirst("select * from Reader where [����֤��]='" + numerid2 + "'");
		if(reader!=null){
			if(reader.get������()==0){
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
