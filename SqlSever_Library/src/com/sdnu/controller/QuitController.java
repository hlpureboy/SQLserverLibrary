package com.sdnu.controller;

import com.jfinal.core.Controller;

public class QuitController extends Controller {
	public void index() {
		removeSessionAttr("admin");
		removeSessionAttr("reader");
		removeSessionAttr("person");
		redirect("/login");
	}
}
