package com.sdnu.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBookS<M extends BaseBookS<M>> extends Model<M> implements IBean {

	public void setͼ��ID(java.lang.String ͼ��ID) {
		set("ͼ��ID", ͼ��ID);
	}

	public java.lang.String getͼ��ID() {
		return get("ͼ��ID");
	}

	public void setISBN(java.lang.String ISBN) {
		set("ISBN", ISBN);
	}

	public java.lang.String getISBN() {
		return get("ISBN");
	}

	public void set�Ƿ���(java.lang.Boolean �Ƿ���) {
		set("�Ƿ���", �Ƿ���);
	}

	public java.lang.Boolean get�Ƿ���() {
		return get("�Ƿ���");
	}

}