package com.sdnu.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLend<M extends BaseLend<M>> extends Model<M> implements IBean {

	public void set借书证号(java.lang.String 借书证号) {
		set("借书证号", 借书证号);
	}

	public java.lang.String get借书证号() {
		return get("借书证号");
	}

	public void setISBN(java.lang.String ISBN) {
		set("ISBN", ISBN);
	}

	public java.lang.String getISBN() {
		return get("ISBN");
	}

	public void set图书ID(java.lang.String 图书ID) {
		set("图书ID", 图书ID);
	}

	public java.lang.String get图书ID() {
		return get("图书ID");
	}

	public void set借书时间(java.lang.String 借书时间) {
		set("借书时间", 借书时间);
	}

	public java.lang.String get借书时间() {
		return get("借书时间");
	}

	public void set应还时间(java.lang.String 应还时间) {
		set("应还时间", 应还时间);
	}

	public java.lang.String get应还时间() {
		return get("应还时间");
	}

}
