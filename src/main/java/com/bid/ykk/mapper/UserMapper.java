package com.bid.ykk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bid.ykk.entity.User;

import java.util.List;
public interface UserMapper extends BaseMapper<User> {
    List<User> getUser();
}
