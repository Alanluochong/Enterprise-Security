package org.luo.enterprise.entity;

import java.io.Serializable;

public class KDC implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tname;
	private String skey;
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
}
