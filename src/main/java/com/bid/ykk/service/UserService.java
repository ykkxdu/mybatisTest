package com.bid.ykk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bid.ykk.entity.User;
public interface UserService extends IService<User> {
    IPage<User> getAllUser(Page page);
    User findByUsername(String username);
}
