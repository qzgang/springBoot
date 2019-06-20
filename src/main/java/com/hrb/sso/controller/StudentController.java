package com.hrb.sso.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hrb.sso.entity.Student;
import com.hrb.sso.service.StudentService;
import com.hrb.sso.vo.StudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 */
@Api("对学生表CRUD")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("添加学生")
    @PostMapping("/add")
    public String add(@RequestBody StudentVo student){
        Student stu = new Student();
        return studentService.insert(stu)?"添加成功":"添加失败";
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/delete/{id}")
    public String delete(@ApiParam("学生的主键id")@PathVariable(value = "id") Integer id){
        return studentService.deleteById(id)?"删除成功":"删除失败";
    }

    @ApiOperation("修改学生")
    @PostMapping("/update")
    public String update(@RequestBody Student student){
        return studentService.updateById(student)?"修改成功":"修改失败";
    }

    @ApiOperation(value = "查询学生")
    @GetMapping("/list")
    public List<Student> list(){
        Page<Student> page = new Page<>();
        page.setSize(10);
        page.setCurrent(0);
        return studentService.selectPage(page).getRecords();
    }
}

