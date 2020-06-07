package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程对外服务接口
 */
@Slf4j
@RestController
public class HomepageCourseController {

    /** 课程服务接口 */
    private final ICourseService courseService;

    public HomepageCourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(Long id){
        log.info("get course id -> {}", id);
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request){
        log.info("get courses -> {}", JSON.toJSONString(request));
        return courseService.getCourses(request);
    }
}
