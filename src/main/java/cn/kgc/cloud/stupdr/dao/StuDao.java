package cn.kgc.cloud.stupdr.dao;

import entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StuDao {

    int getCount();

    List<Student> showStu(@Param("start")int start,@Param("pageSize")int pageSize);

    Student getStuById(@Param("sid")Integer sid);

    int addStu(@Param("stu") Student student);

    int modifyStu(@Param("stu") Student student);

    int delStu(@Param("sid") Integer sid);
}
