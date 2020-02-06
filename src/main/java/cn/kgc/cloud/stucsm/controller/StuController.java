package cn.kgc.cloud.stucsm.controller;

import cn.kgc.cloud.stucsm.service.StuService;
import com.alibaba.fastjson.JSON;
import entity.Result;
import entity.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: stu-csm
 * @description: TODO
 * @author: cuihao
 * @create: 2020-02-04 14:44
 * @version: V1.0
 **/
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class StuController {
    @Resource
    private StuService stuService;

    /*@RequestMapping("/getAll")
    public Result getAll(int currentPage, int pageSize) {
        Result result = new Result();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("currentPage",currentPage);
        hashMap.put("pageSize",pageSize);
        Map<String, Object> map = stuService.showStu(hashMap);
        result.setCode(2001);
        result.setMessage("请求成功");
        result.setData(JSON.toJSONString(map));
        return result;
    }*/

    @RequestMapping("/getAll")
    public Result getAll(int currentPage, int pageSize) {
        Result result = new Result();
        Map<String, Object> map = stuService.showStu(currentPage, pageSize);
        if (null == map) {
            result.setCode(2002);
            result.setMessage("请求失败成功");
            return result;
        }
        result.setCode(2001);
        result.setMessage("请求成功");
        result.setData(JSON.toJSONString(map));
        return result;
    }

    @RequestMapping("/getStuById")
    public Result getStu(Integer sid) {
        Result result = new Result();
        Student stu = stuService.getStu(sid);
        if (stu == null) {
            result.setCode(2002);
            result.setMessage("请求失败");
        }
        result.setCode(2001);
        result.setMessage("请求成功");
        result.setData(JSON.toJSONString(stu));
        return result;
    }

    @RequestMapping("/add")
    public Result addStu(@RequestBody Student student) {
        Result result = new Result();
        boolean flag = stuService.addStu(student);
        if (flag) {
            result.setCode(2001);
            result.setMessage("添加成功");
            return result;
        }
        result.setCode(2002);
        result.setMessage("添加失败");
        return result;
    }

    @RequestMapping("/update")
    public Result updateStu(@RequestBody Student student) {
        Result result = new Result();
        boolean flag = stuService.updateStu(student);
        if (flag) {
            result.setCode(2001);
            result.setMessage("修改成功");
            return result;
        }
        result.setCode(2002);
        result.setMessage("修改失败");
        return result;
    }

    @RequestMapping("/del")
    public Result delStu(Integer sid) {
        Result result = new Result();
        boolean flag = stuService.delStu(sid);
        if (flag) {
            result.setCode(2001);
            result.setMessage("删除成功");
            return result;
        }
        result.setCode(2002);
        result.setMessage("删除失败");
        return result;
    }
}
