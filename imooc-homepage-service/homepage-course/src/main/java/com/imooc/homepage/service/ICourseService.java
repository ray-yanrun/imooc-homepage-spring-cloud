package com.imooc.homepage.service;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;

import java.util.List;

/**
 * 课程相关服务接口定义
 */
public interface ICourseService {

    /**
     * 通过ID获取课程信息
     * @param id 课程ID
     * @return 课程信息
     */
    CourseInfo getCourseInfo(Long id);

    /**
     * 通过课程请求对象获取课程列表
     * @param courseInfosRequest 课程请求对象
     * @return 课程列表
     */
    List<CourseInfo> getCourses(CourseInfosRequest courseInfosRequest);
}
