package com.sdnu.controller;


import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

public class DatabaseController extends Controller {
	public void backup() {
		String sql = "exec backupdata";
		Db.update(sql);
		redirect("/admin");
	}
	public void restore() {
		String sql = "ALTER DATABASE [Libary] SET OFFLINE WITH ROLLBACK IMMEDIATE;use master;RESTORE DATABASE [Libary] from DISK='D:\tushu.bak' with replace;ALTER  database  [Libary]  set   online ";
		Db.update(sql);
		redirect("/admin");
	}
}
