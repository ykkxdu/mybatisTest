package com.bid.ykk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bid.ykk.entity.User;
import com.bid.ykk.mapper.UserMapper;
import com.bid.ykk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   @Autowired
   private UserMapper userMapper;
    @Override
    public List<User> getAllUser() {
        return userMapper.getUser();
    }
}
