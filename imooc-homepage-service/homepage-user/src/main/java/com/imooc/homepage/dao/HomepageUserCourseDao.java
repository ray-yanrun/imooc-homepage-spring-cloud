package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * HomepageUserCourse 数据表访问接口定义
 */
public interface HomepageUserCourseDao extends JpaRepository<HomepageUserCourse, Long> {

    /**
     * 根据UserId查询用户课程关联信息
     * @param userId 用户ID
     * @return 用户课程关联信息
     */
    List<HomepageUserCourse> findAllByUserId(Long userId);
}
