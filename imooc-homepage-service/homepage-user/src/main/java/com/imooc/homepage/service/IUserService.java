package com.imooc.homepage.service;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;

import java.util.List;

/**
 * 用户服务接口
 */
public interface IUserService {

    /**
     * 创建用户
     * @param request 请求信息
     * @return 用户信息
     */
    UserInfo createUser(CreateUserRequest request);

    /**
     * 获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    UserInfo getUserInfo(Long id);

    /**
     * 获取用户课程信息
     * @param id 用户ID
     * @return 用户课程信息
     */
    UserCourseInfo getUserCourseInfo(Long id);
}
