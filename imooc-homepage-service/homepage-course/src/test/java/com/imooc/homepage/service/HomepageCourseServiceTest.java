package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.Application;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.dao.HomepageCourseDao;
import com.imooc.homepage.entity.HomepageCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * 课程服务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HomepageCourseServiceTest {

    @Autowired
    private HomepageCourseDao courseDao;

    @Autowired
    private ICourseService courseService;

    @Test
    public void testCreateCourseInfo(){
        HomepageCourse course1 = new HomepageCourse(
                "JDK11特性解读", 0,
                "http://www.imooc.com", "解读JDK11新特性"
        );

        HomepageCourse course2 = new HomepageCourse(
                "Spring广告系统设计", 1,
                "http://www.imooc.com", "Spring"
        );
        System.out.println(courseDao.saveAll(Arrays.asList(course1, course2)).size());
    }

    @Test
    public void testGetCourseInfo(){
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(11L)
        ));
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(8L)
        ));
    }

    @Test
    public void testGetCourseInfos(){
        System.out.println(JSON.toJSONString(
                courseService.getCourses(new CourseInfosRequest(Arrays.asList(11L, 12L)))
        ));
    }
}
