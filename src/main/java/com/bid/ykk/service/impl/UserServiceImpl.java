package com.bid.ykk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public IPage<User> getAllUser(Page page) {
        return userMapper.getUser(page);
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        return userMapper.selectOne(queryWrapper);
    }
}
