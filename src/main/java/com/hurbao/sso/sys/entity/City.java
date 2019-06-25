package com.hurbao.sso.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 城市表
 * </p>
 *
 * @author ${author}
 * @since 2019-06-25
 */
@Data
@Accessors(chain = true)
@TableName("SYS_CITY")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("ID")
    private String id;
    /**
     * 所属省份
     */
    @TableField("SPROVINCEID")
    private String sprovinceid;
    /**
     * 城市名
     */
    @TableField("SNAME")
    private String sname;
    /**
     * 显示顺序
     */
    @TableField("ISORT")
    private Long isort;
    /**
     * 简拼名
     */
    @TableField("SJPNAME")
    private String sjpname;
    /**
     * 全拼名
     */
    @TableField("SPYNAME")
    private String spyname;
    /**
     * 是否省会
     */
    @TableField("BISPROVINCECITY")
    private Integer bisprovincecity;
    /**
     * 是否常用
     */
    @TableField("BISCOMMON")
    private Integer biscommon;
    /**
     * 区号
     */
    @TableField("SAREACODE")
    private String sareacode;
    /**
     * 身份证区位号
     */
    @TableField("SIDCODE")
    private String sidcode;


}
