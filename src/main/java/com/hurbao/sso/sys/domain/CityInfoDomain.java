package com.hurbao.sso.sys.domain;


public class CityInfoDomain  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1571246703084722870L;

	/**
     * 国家名称
     */
    private String sname;

    /**
     * 国家简拼名
     */
    private String sjp;

    /**
     * 国家全拼名
     */
    private String spn;

    /**
     * 省份名
     */
    private String pname;

    /**
     * 省份排序
     */
    private Integer psort;

    /**
     * 省份简拼名
     */
    private String pjp;

    /**
     * 省份全拼名
     */
    private String ppn;

    /**
     * 城市名称
     */
    private String cname;

    /**
     * 城市排序字段
     */
    private Integer csort;

    /**
     * 城市简拼名
     */
    private String cjp;

    /**
     * 城市全拼名
     */
    private String cpn;

    /**
     * 是否省会 1：省会 0：非省会
     * 
     */
    private Integer cispc;

    /**
     * 城市代码
     */
    private String cacode;
    
    /**
     * 省份ID
     */
    private String proviceId;
    
    /**
     * 城市Id
     */
    private String cityId;

    /**
     * 省份对应省份证号代码
     */
    private String pidcode;
    /**
     * 城市对应省份证号代码
     */
    private String cidcode;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSjp() {
		return sjp;
	}
	public void setSjp(String sjp) {
		this.sjp = sjp;
	}
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getPjp() {
		return pjp;
	}
	public void setPjp(String pjp) {
		this.pjp = pjp;
	}
	public String getPpn() {
		return ppn;
	}
	public void setPpn(String ppn) {
		this.ppn = ppn;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getCjp() {
		return cjp;
	}
	public void setCjp(String cjp) {
		this.cjp = cjp;
	}
	public String getCpn() {
		return cpn;
	}
	public void setCpn(String cpn) {
		this.cpn = cpn;
	}
	public Integer getCispc() {
		return cispc;
	}
	public void setCispc(Integer cispc) {
		this.cispc = cispc;
	}
	public String getCacode() {
		return cacode;
	}
	public void setCacode(String cacode) {
		this.cacode = cacode;
	}
	public String getProviceId() {
		return proviceId;
	}
	public void setProviceId(String proviceId) {
		this.proviceId = proviceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getPidcode() {
		return pidcode;
	}
	public void setPidcode(String pidcode) {
		this.pidcode = pidcode;
	}
	public String getCidcode() {
		return cidcode;
	}
	public void setCidcode(String cidcode) {
		this.cidcode = cidcode;
	}
	public Integer getPsort() {
		return psort;
	}
	public void setPsort(Integer psort) {
		this.psort = psort;
	}
	public Integer getCsort() {
		return csort;
	}
	public void setCsort(Integer csort) {
		this.csort = csort;
	}
	
	
   
}
