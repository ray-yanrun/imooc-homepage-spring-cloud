package com.imooc.homepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基本的用户信息，后面用户模块和课程模块都会用到
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private String email;

    public static UserInfo invalid(){
        return new UserInfo(-1L, "", "");
    }
}
