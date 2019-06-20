package com.hrb.sso.rz.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 统一操作记录表
 * </p>
 *
 * @author ${author}
 * @since 2019-06-20
 */
@TableName("rz_bus_record")
public class RzBusRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
    private String id;
    /**
     * 会员ID
     */
    @TableField("SMEMBER_ID")
    private String smemberId;
    /**
     * 记录名
     */
    @TableField("SNAME")
    private String sname;
    /**
     * 代码
     */
    @TableField("SGROUP_NO")
    private String sgroupNo;
    /**
     * 数字值
     */
    @TableField("INUMBER_VALUE")
    private BigDecimal inumberValue;
    /**
     * 说明
     */
    @TableField("SREMARK")
    private String sremark;
    /**
     * 添加日期
     */
    @TableField("DADD_DATE")
    private Date daddDate;
    /**
     * 修改日期
     */
    @TableField("DMODIFY_DATE")
    private Date dmodifyDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmemberId() {
        return smemberId;
    }

    public void setSmemberId(String smemberId) {
        this.smemberId = smemberId;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSgroupNo() {
        return sgroupNo;
    }

    public void setSgroupNo(String sgroupNo) {
        this.sgroupNo = sgroupNo;
    }

    public BigDecimal getInumberValue() {
        return inumberValue;
    }

    public void setInumberValue(BigDecimal inumberValue) {
        this.inumberValue = inumberValue;
    }

    public String getSremark() {
        return sremark;
    }

    public void setSremark(String sremark) {
        this.sremark = sremark;
    }

    public Date getDaddDate() {
        return daddDate;
    }

    public void setDaddDate(Date daddDate) {
        this.daddDate = daddDate;
    }

    public Date getDmodifyDate() {
        return dmodifyDate;
    }

    public void setDmodifyDate(Date dmodifyDate) {
        this.dmodifyDate = dmodifyDate;
    }

    @Override
    public String toString() {
        return "RzBusRecord{" +
        ", id=" + id +
        ", smemberId=" + smemberId +
        ", sname=" + sname +
        ", sgroupNo=" + sgroupNo +
        ", inumberValue=" + inumberValue +
        ", sremark=" + sremark +
        ", daddDate=" + daddDate +
        ", dmodifyDate=" + dmodifyDate +
        "}";
    }
}
