package com.hrb.sso.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hrb.sso.entity.Student;
import com.hrb.sso.mapper.StudentMapper;
import com.hrb.sso.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
