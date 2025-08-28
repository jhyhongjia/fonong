package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {
    private final static long serialVersionUID = 1L;

    private Long id;

    private Integer userType;

    private String account;

    private String password;

    private String name;

    private Integer isDeleted;
}
