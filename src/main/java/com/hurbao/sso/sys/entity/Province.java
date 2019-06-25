package com.hurbao.sso.sys.entity;

/** 省份表(PROVINCE) **/
public class Province{

	private static final long serialVersionUID = 1L;
	
	//-----private-----
	private String id;   //主键
	private String scountryid;   //所属国家
	private String sname;   //省份名
	private Integer isort;   //显示顺序
	private String sjpname;   //简拼名
	private String spyname;   //全拼名
	private String sidcode;   //身份证区位号
	
	//-----get set-----
	 /**主键*/
	 public String getId(){
		 return id;
	 }
	 /**主键*/
	 public void setId(String id){
		 this.id=id;
	 }
	 /**所属国家*/
	 public String getScountryid(){
		 return scountryid;
	 }
	 /**所属国家*/
	 public void setScountryid(String scountryid){
		 this.scountryid=scountryid;
	 }
	 /**省份名*/
	 public String getSname(){
		 return sname;
	 }
	 /**省份名*/
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
	 /**身份证区位号*/
	 public String getSidcode(){
		 return sidcode;
	 }
	 /**身份证区位号*/
	 public void setSidcode(String sidcode){
		 this.sidcode=sidcode;
	 }
	

		
} 