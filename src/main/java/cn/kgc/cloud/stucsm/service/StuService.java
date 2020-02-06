package cn.kgc.cloud.stucsm.service;

import cn.kgc.cloud.stucsm.service.impl.StuServiceImpl;
import entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "stu-pdr",fallback = StuServiceImpl.class)
public interface StuService {

   /* @RequestMapping("/showStu")
    Map<String, Object> showStu(@RequestBody Map<String,Object> hashMap);*/

    @RequestMapping("/showStu")
    Map<String, Object> showStu(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize);

    @RequestMapping("/getStu")
    Student getStu(@RequestParam("sid") Integer sid);

    @RequestMapping(value = "/addStu")
    boolean addStu(@RequestBody Student student);

    @RequestMapping(value = "/updateStu")
    boolean updateStu(@RequestBody Student student);

    @RequestMapping("/delStu")
    boolean delStu(@RequestParam("sid") Integer sid);
}
