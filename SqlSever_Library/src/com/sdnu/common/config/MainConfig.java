package com.sdnu.common.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.sdnu.common.model._MappingKit;
import com.sdnu.controller.AdminController;
import com.sdnu.controller.BookController;
import com.sdnu.controller.DatabaseController;
import com.sdnu.controller.LoginController;
import com.sdnu.controller.QuitController;
import com.sdnu.controller.ReaderController;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.render.ViewType;

public class MainConfig extends JFinalConfig {
	
	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("devMode"));
		me.setBaseUploadPath("upload/temp/");
		me.setBaseDownloadPath("download");
		me.setViewType(ViewType.JFINAL_TEMPLATE);
		//me.setError404View("404.html");
		me.setJsonFactory(FastJsonFactory.me());
		
	}
	
	@Override
	public void configRoute(Routes me) {
	    me.add("/login", LoginController.class);
	    me.add("/admin", AdminController.class);
	    me.add("/book", BookController.class);
	    me.add("/reader", ReaderController.class);
	    me.add("/quit",QuitController.class);
	    me.add("/data",DatabaseController.class);
	}
	
	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin dbPlugin=new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		ActiveRecordPlugin arp=new ActiveRecordPlugin(dbPlugin);
		arp.setShowSql(PropKit.getBoolean("devMode"));
		dbPlugin.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		arp.setDialect(new SqlServerDialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		_MappingKit.mapping(arp);
		me.add(dbPlugin);
		me.add(arp);
	}
	@Override
	public void configInterceptor(Interceptors me) {

	}
	@Override
	public void configHandler(Handlers me) {
		 me.add(new BasePathHandler());
	}
	
	@Override
	public void configEngine(Engine me) {
		//me.addSharedFunction("/view/common/layout.html")
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}
	

}
