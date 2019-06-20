package com.hrb.sso.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 */
@Api("学生实体对象")
@Data
@TableName("HY_MEMBER_INFO")
public class Student {

    @ApiModelProperty("学生id")
    @TableId(type = IdType.INPUT)
    private String id;
}
