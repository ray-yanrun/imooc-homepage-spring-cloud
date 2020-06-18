package com.imooc.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * 创建用户请求信息对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;
    private String email;

    /**
     * 请求有效性验证
     * @return 真假值
     */
    public boolean validate(){
        return StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(email);
    }
}
