package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.client.CourseClient;
import com.imooc.homepage.dao.HomepageUserCourseDao;
import com.imooc.homepage.dao.HomepageUserDao;
import com.imooc.homepage.entity.HomepageUser;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.service.IUserService;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final HomepageUserDao userDao;
    private final HomepageUserCourseDao userCourseDao;
    private final CourseClient courseClient;

    @Autowired
    public UserServiceImpl(HomepageUserDao userDao, HomepageUserCourseDao userCourseDao, CourseClient courseClient) {
        this.userDao = userDao;
        this.userCourseDao = userCourseDao;
        this.courseClient = courseClient;
    }

    @Override
    public UserInfo createUser(CreateUserRequest request) {
        if(!request.validate()){
            return UserInfo.invalid();
        }
        HomepageUser oldUser = userDao.findByUsername(request.getUsername());
        if(oldUser != null){ // 用户已经存在
            return UserInfo.invalid();
        }
        HomepageUser newUser = userDao.save(new HomepageUser(request.getUsername(), request.getEmail()));
        return new UserInfo(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        Optional<HomepageUser> user = userDao.findById(id);
        if(!user.isPresent()){
            return UserInfo.invalid();
        }
        HomepageUser currentUser = user.get();
        return new UserInfo(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail());
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {
        Optional<HomepageUser> user = userDao.findById(id);
        if(!user.isPresent()){
            return UserCourseInfo.invalid();
        }
        HomepageUser currentUser = user.get();
        UserInfo userInfo = new UserInfo(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail());
        List<HomepageUserCourse> userCourses = userCourseDao.findAllByUserId(id);
        if(CollectionUtils.isEmpty(userCourses)){
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }
        List<CourseInfo> courseInfos = courseClient.getCourseInfos(new CourseInfosRequest(
                userCourses.stream().map(HomepageUserCourse::getCourseId).collect(Collectors.toList())
        ));
        return new UserCourseInfo(userInfo, courseInfos);
    }
}
