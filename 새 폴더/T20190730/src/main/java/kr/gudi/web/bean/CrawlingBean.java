package kr.gudi.web.bean;

import java.util.HashMap;

public class CrawlingBean {
	
	String queryType;
	String queryTarget;
	String url;
	String target;
	HashMap<String, Object> columns;
	
	public CrawlingBean(String queryType, String queryTarget, String url, String target, HashMap<String, Object> columns) {
		setQueryType(queryType);
		setQueryTarget(queryTarget);
		setUrl(url);
		setTarget(target);
		setColumns(columns);
	}
	
	public String getQueryType() {
		return queryType;
	}
	private void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryTarget() {
		return queryTarget;
	}
	private void setQueryTarget(String queryTarget) {
		this.queryTarget = queryTarget;
	}
	public String getUrl() {
		return url;
	}
	private void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	private void setTarget(String target) {
		this.target = target;
	}
	public HashMap<String, Object> getColumns() {
		return columns;
	}
	private void setColumns(HashMap<String, Object> columns) {
		this.columns = columns;
	}
	@Override
	public String toString() {
		return "CrawlingBean [queryTarget=" + queryTarget + ", url=" + url + ", target=" + target + ", columns="
				+ columns + "]";
	}
}
