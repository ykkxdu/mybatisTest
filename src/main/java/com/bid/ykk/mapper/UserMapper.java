package com.bid.ykk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bid.ykk.entity.User;

import java.util.List;
public interface UserMapper extends BaseMapper<User> {
    IPage<User> getUser(Page page);
}
