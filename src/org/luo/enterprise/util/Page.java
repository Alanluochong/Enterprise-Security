package org.luo.enterprise.util;

public class Page {
	private Integer page=1;
	private Integer pageSize=5;
	//×î´óÒ³Êý
	private Integer totalPage=1;
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getBegin(){
		return (page-1)*pageSize;
	}
	public Integer getEnd(){
		return page*pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
