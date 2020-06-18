package com.imooc.homepage.client;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 调用课程微服务时的熔断降级策略
 */
@Component
public class CourseClientHystrix implements CourseClient {

    /** 获取课程信息发生熔断 返回无效的课程 */
    @Override
    public CourseInfo getCourseInfo(Long id) {
        return CourseInfo.invalid();
    }

    /** 获取课程信息列表发生熔断 返回空列表 */
    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        return Collections.emptyList();
    }
}
