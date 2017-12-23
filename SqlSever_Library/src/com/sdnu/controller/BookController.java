package com.sdnu.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.ICallback;
import com.jfinal.plugin.activerecord.Record;
import com.sdnu.common.model.ADMIN;
import com.sdnu.common.model.Book;
import com.sdnu.common.model.BookS;
import com.sdnu.common.model.Lend;
import com.sdnu.common.model.Reader;


public class BookController extends Controller {
	public void index() {
		//ADMIN admin = getSessionAttr("admin");
		//setAttr("name",admin.get用户名());
		render("/Library/admin.html");
	}
	public void addBook() {
		JSONObject js = new JSONObject();
		String book_name = getPara("book_name");
		String isbn = getPara("isbn");
		String publish = getPara("publish");
		String author = getPara("author");
		int fnumber = getParaToInt("fnumber");
		int knumber = getParaToInt("knumber");
		Book book = getModel(Book.class, "book");
		book.setISBN(isbn);
		book.set书名(book_name);
		book.set出版社(publish);
		book.set作者(author);
		book.set复本量(fnumber);
		book.set库存量(knumber);
		book.save();
		js.put("status", "success");
		renderJson(js.toString());
	}
	@SuppressWarnings("unused")
	public void changeBook() {
		JSONObject js = new JSONObject();
		String cisbn = getPara("cisbn");
		int cknumber = getParaToInt("cknumber");
		Book book = Book.dao.findFirst("select * from Book where ISBN='" + cisbn + "'");
		System.out.println(book.get书名());
		if(book!=null){
			try {
				Db.execute(new ICallback() {
					
					@Override
					public Object call(Connection conn) throws SQLException {
						// TODO Auto-generated method stub
						CallableStatement proc = null; 
						proc =  conn.prepareCall("{ call changebooknumber(?,?) }");
						proc.setInt(1, cknumber);
						proc.setString(2, cisbn);
						proc.execute();
						return null;
					}
				});
			    js.put("status", "success");
				renderJson(js.toString());
			} catch (Exception e) {
				js.put("status", "default1");
				renderJson(js.toString());
			}
		}else{
			js.put("status", "default2");
			renderJson(js.toString());
		}
	}
	public void delBook() {
		JSONObject js = new JSONObject();
		String disbn = getPara("disbn");
		Book book = Book.dao.findFirst("select * from Book where ISBN='" + disbn + "'");
		if(book!=null){
			if(book.get复本量()==book.get库存量()){
			book.delete();
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
	@SuppressWarnings("unused")
	public void searchBook() {
		JSONObject js = new JSONObject();
		String bookname = getPara("bookname");
		List<Record> record = Db.query("select * from BookSearch where [书名] like '%"+bookname+"%'");
		System.out.println(record.size());
		if(record!=null){
			//setAttr("Record", record);
			js.put("Record", record);
		    js.put("status", "success");
			renderJson(js.toString());
		}else{
			js.put("status", "default");
			renderJson(js.toString());
		}
		
	}
	public void lendBook() {
		JSONObject js = new JSONObject();
		Reader reader = getSessionAttr("reader");
		String num = reader.get借书证号();
		String id = getPara("id");
		BookS books = BookS.dao.findFirst("select * from BookS where [图书ID]='" + id + "'");
		String isbn = books.getISBN();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String date = sdf.format(now);
		Db.execute(new ICallback() {
			
			@Override
			public Object call(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				CallableStatement proc = null; 
				proc =  conn.prepareCall("{ call lendbook(?,?,?,?) }");
				proc.setString(1, num);
				proc.setString(2, id);
				proc.setString(3, isbn);
				proc.setString(4, date);
				proc.execute();
				return null;
			}
		});
	    js.put("status", "success");
	    renderJson(js.toString());
	}
	
	@SuppressWarnings("unused")
	public void showLend() {
		JSONObject js = new JSONObject();
		Reader reader = getSessionAttr("reader");
		String num = reader.get借书证号();
		List<Record> record = Db.query("select * from ReadersLend where [借书证号] like '%"+num+"%'");
		System.out.println(record.size());
		if(record!=null){
			//setAttr("Record", record);
			js.put("Record", record);
		    js.put("status", "success");
			renderJson(js.toString());
		}else{
			js.put("status", "default");
			renderJson(js.toString());
		}
	}
	
	public void RLendBook() {
		JSONObject js = new JSONObject();
		Reader reader = getSessionAttr("reader");
		String num = reader.get借书证号();
		String id = getPara("id");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(now);
		System.out.println(id);
		System.out.println(date);
		Db.execute(new ICallback() {
			
			@Override
			public Object call(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				CallableStatement proc = null; 
				proc =  conn.prepareCall("{ call bookreturn(?,?,?) }");
				proc.setString(1, num);
				proc.setString(2, id);
				proc.setString(3, date);
				proc.execute();
				return null;
			}
		});
	    js.put("status", "success");
	    renderJson(js.toString());
	}
	
	@SuppressWarnings("unused")
	public void history() {
		JSONObject js = new JSONObject();
		Reader reader = getSessionAttr("reader");
		String num = reader.get借书证号();
		List<Record> record = Db.query("select * from ReaderRlend where [借书证号] like '%"+num+"%'");
		System.out.println(record.size());
		if(record!=null){
			//setAttr("Record", record);
			js.put("Record", record);
		    js.put("status", "success");
			renderJson(js.toString());
		}else{
			js.put("status", "default");
			renderJson(js.toString());
		}
	}
}
