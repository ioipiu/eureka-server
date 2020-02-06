package cn.kgc.cloud.stupdr.service;

import cn.kgc.cloud.stupdr.dao.StuDao;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: stu-pdr
 * @description: TODO
 * @author: cuihao
 * @create: 2020-02-04 14:54
 * @version: V1.0
 **/
@RestController
public class StuService {

    @Autowired
    private StuDao stuDao;

    /*@RequestMapping("/showStu")
    public Map<String, Object> showStu(@RequestBody Map<String,Object> hashMap) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println("当前页："+hashMap.get("currentPage"));
        System.out.println("每页数量"+hashMap.get("pageSize"));
        Integer currentPage = (Integer)hashMap.get("currentPage");
        Integer pageSize = (Integer)hashMap.get("pageSize");
        int start = ( currentPage- 1) * pageSize;
        List<Student> students = stuDao.showStu(start, pageSize);
        int total = stuDao.getCount();
        map.put("students", students);
        map.put("total", total);
        return map;
    }*/

    @RequestMapping("/showStu")
    public Map<String, Object> showStu(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        int start = (currentPage - 1) * pageSize;
        List<Student> students = stuDao.showStu(start, pageSize);
        int total = stuDao.getCount();
        map.put("students", students);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/getStu")
    public Student getStu(@RequestParam("sid") Integer sid) {
        Student student = stuDao.getStuById(sid);
        return student;
    }

    @RequestMapping("/addStu")
    public boolean addStu(@RequestBody Student student) {
        int i = stuDao.addStu(student);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @RequestMapping("/updateStu")
    public boolean updateStu(@RequestBody Student student) {
        int i = stuDao.modifyStu(student);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @RequestMapping("/delStu")
    public boolean delStu(@RequestParam("sid") Integer sid) {
        int i = stuDao.delStu(sid);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
