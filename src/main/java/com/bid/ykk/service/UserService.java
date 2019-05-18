package com.bid.ykk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bid.ykk.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getAllUser();
}
