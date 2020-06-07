package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.dao.HomepageCourseDao;
import com.imooc.homepage.entity.HomepageCourse;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 课程服务功能实现
 */
@Slf4j
@Service
public class CourseServiceImpl implements ICourseService {

    private final HomepageCourseDao homepageCourseDao;

    // 使用构造函数的方式注入Dao
    public CourseServiceImpl(HomepageCourseDao homepageCourseDao) {
        this.homepageCourseDao = homepageCourseDao;
    }

    @Override
    public CourseInfo getCourseInfo(Long id) {
        Optional<HomepageCourse> course = homepageCourseDao.findById(id);
        return buildCourseInfo(course.orElse(HomepageCourse.invalid())); // 如果course为空则返回无效的课程
    }

    @Override
    public List<CourseInfo> getCourses(CourseInfosRequest courseInfosRequest) {
        // 如果课程请求对象的ID列表为空，则直接返回空列表
        if(CollectionUtils.isEmpty(courseInfosRequest.getIds())){
            return Collections.emptyList();
        }
        List<HomepageCourse> courseList = homepageCourseDao.findAllById(courseInfosRequest.getIds());
        return courseList.stream()  //Java8的语法
                .map(this::buildCourseInfo)  // 将集合中的每个元素转换为CourseInfo
                .collect(Collectors.toList());  // 将所有的CourseInfo转成List
    }

    /**
     * 根据数据记录构造对象信息(实体对象转VO对象)
     * @param course 课程数据记录
     * @return 课程对象信息
     */
    private CourseInfo buildCourseInfo(HomepageCourse course){
        return CourseInfo.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType()==0?"免费课程":"实战课程")
                .courseIcon(course.getCourseIcon())
                .courseIntro(course.getCourseIntro())
                .build();
    }
}
