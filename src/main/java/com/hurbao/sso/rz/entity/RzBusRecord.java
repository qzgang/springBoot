package com.hurbao.sso.rz.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 统一操作记录表
 * </p>
 *
 * @author ${author}
 * @since 2019-06-20
 */
@Data
@Accessors(chain = true)
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


}
