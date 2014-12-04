package com.power.data;

public class NewsData extends Object{

	private String m_id = null;
	private String m_title = null ; 
	private String m_commentCount = null;
	private String m_author =null;
	private String m_authorid  =null;
	private String m_pubDate =null;
	private String m_url  = null;
	private String m_type = null;
	private String m_authoruid2 = null;

	public NewsData() {
		// TODO Auto-generated constructor stub
	}
	

	public NewsData(String m__id, String m__title, String m__commentCount,
			String m_author, String m_authorid, String m_pubDate, String m_url,
			String m_type, String m_authoruid2) {
		super();
		this.m_id = m__id;
		this.m_title = m__title;
		this.m_commentCount = m__commentCount;
		this.m_author = m_author;
		this.m_authorid = m_authorid;
		this.m_pubDate = m_pubDate;
		this.m_url = m_url;
		this.m_type = m_type;
		this.m_authoruid2 = m_authoruid2;
	}



	public String getM__id() {
		return m_id;
	}

	public void setM__id(String m__id) {
		this.m_id = m__id;
	}

	public String getM__title() {
		return m_title;
	}

	public void setM__title(String m__title) {
		this.m_title = m__title;
	}

	public String getM_commentCount() {
		return m_commentCount;
	}

	public void setM_commentCount(String m__commentCount) {
		this.m_commentCount = m__commentCount;
	}

	public String getM_author() {
		return m_author;
	}

	public void setM_author(String m_author) {
		this.m_author = m_author;
	}

	public String getM_authorid() {
		return m_authorid;
	}

	public void setM_authorid(String m_authorid) {
		this.m_authorid = m_authorid;
	}

	public String getM_pubDate() {
		return m_pubDate;
	}

	public void setM_pubDate(String m_pubDate) {
		this.m_pubDate = m_pubDate;
	}

	public String getM_url() {
		return m_url;
	}

	public void setM_url(String m_url) {
		this.m_url = m_url;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}

	public String getM_authoruid2() {
		return m_authoruid2;
	}

	public void setM_authoruid2(String m_authoruid2) {
		this.m_authoruid2 = m_authoruid2;
	}
	
}
