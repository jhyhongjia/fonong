package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    boolean login(String account, String password);

}
