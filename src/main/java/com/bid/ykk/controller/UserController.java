package com.bid.ykk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bid.ykk.entity.User;
import com.bid.ykk.mapper.UserMapper;
import com.bid.ykk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "用户的增删改查")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("得到所有的用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name="current",value="当前页数",required = true,dataType = "Integer"),
                        @ApiImplicitParam(name="size",value="每页的大小",required = true,dataType = "Integer")})
    @RequestMapping("/getAllUser")
    public List<User> getAllUser(){
        return  userService.getAllUser();
    }

}
