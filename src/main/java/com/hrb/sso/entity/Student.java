package com.hrb.sso.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("学生实体对象")
@Data
@TableName("HY_MEMBER_INFO")
public class Student {

    @ApiModelProperty("学生id")
    @TableId(type = IdType.INPUT)
    private String id;

    @ApiModelProperty("学生姓名")
    private String name;

    @ApiModelProperty("学生年龄")
    private Integer age;

    @ApiModelProperty("学生班级")
    private String classname;

}
