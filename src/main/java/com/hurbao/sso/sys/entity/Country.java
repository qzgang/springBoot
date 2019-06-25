package com.hurbao.sso.sys.entity;


/** 国家表(COUNTRY) **/
public class Country {

	private static final long serialVersionUID = 1L;
	
	//-----private-----
	private String id;   //主键ID
	private String sname;   //国家名
	private Integer isort;   //显示顺序
	private String sjpname;   //简拼名
	private String spyname;   //全拼名
	
	//-----get set-----
	 /**主键ID*/
	 public String getId(){
		 return id;
	 }
	 /**主键ID*/
	 public void setId(String id){
		 this.id=id;
	 }
	 /**国家名*/
	 public String getSname(){
		 return sname;
	 }
	 /**国家名*/
	 public void setSname(String sname){
		 this.sname=sname;
	 }
	 /**显示顺序*/
	 public Integer getIsort(){
		 return isort;
	 }
	 /**显示顺序*/
	 public void setIsort(Integer isort){
		 this.isort=isort;
	 }
	 /**简拼名*/
	 public String getSjpname(){
		 return sjpname;
	 }
	 /**简拼名*/
	 public void setSjpname(String sjpname){
		 this.sjpname=sjpname;
	 }
	 /**全拼名*/
	 public String getSpyname(){
		 return spyname;
	 }
	 /**全拼名*/
	 public void setSpyname(String spyname){
		 this.spyname=spyname;
	 }
	

		
} 