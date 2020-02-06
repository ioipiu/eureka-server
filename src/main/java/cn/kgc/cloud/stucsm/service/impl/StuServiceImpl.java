package cn.kgc.cloud.stucsm.service.impl;

import cn.kgc.cloud.stucsm.service.StuService;
import entity.Student;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: stu-csm
 * @description: TODO
 * @author: cuihao
 * @create: 2020-02-05 10:23
 * @version: V1.0
 **/
@Service
public class StuServiceImpl implements StuService {
    @Override
    public Map<String, Object> showStu(int currentPage, int pageSize) {
        System.out.println("进入熔断模式，pdr连接失败！");
        return null;
    }

    @Override
    public Student getStu(Integer sid) {
        return null;
    }

    @Override
    public boolean addStu(Student student) {
        return false;
    }

    @Override
    public boolean updateStu(Student student) {
        return false;
    }

    @Override
    public boolean delStu(Integer sid) {
        return false;
    }
}
