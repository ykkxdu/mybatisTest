package com.bid.ykk.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bid.ykk.entity.User;
import com.bid.ykk.mapper.UserMapper;
import com.bid.ykk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(description = "用户的增删改查")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    /**
     * mybatis-plus传入page进行分页操作。
    * */
    @ApiOperation("得到所有的用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name="current",value="当前页数",required = false,dataType = "Integer"),
                        @ApiImplicitParam(name="size",value="每页的大小",required = false,dataType = "Integer")})
    @RequestMapping("/getAllUser")
    public IPage<User> getAllUser(@RequestParam(value = "current",defaultValue = "1",required = false) int current,
                                  @RequestParam(value = "size",defaultValue = "10",required = false)int size){
        Page page=new Page(current,size);
        // 调用自己的sql语句并进行分页
        //IPage<User> list=userMapper.getUser(page);
        // 调用系统自带的语句
        IPage<User> list=userService.page(page);
        return  list;
    }
    /**
     * QueryWrapper条件构造器的使用
    * */
    @ApiOperation("用户信息中用户名的模糊查询")
    @ApiImplicitParams({@ApiImplicitParam(name="current",value="当前页数",required = false,dataType = "Integer"),
                         @ApiImplicitParam(name="size",value="每页的大小",required = false,dataType = "Integer"),
                         @ApiImplicitParam(name="username",value="用户名",required = false,dataType = "String")
                         })
    @RequestMapping("/getUserLikeUsername")
    public IPage<User> getUserLikeUsername(@RequestParam(value = "current",defaultValue = "1",required = false) int current,
                                           @RequestParam(value = "size",defaultValue = "10",required = false)int size,
                                           @RequestParam(value = "username",required = false)String username){
        Page page=new Page(current,size);
        QueryWrapper queryWrapper=new QueryWrapper();
        //单表查询时，可使用系统自带的分页功能。
        queryWrapper.like("user_name",username);
        IPage<User> list=userService.page(page,queryWrapper);
        // 多表查询时，对查询出来的数据自己做分页查询
//        Page<User> page = new Page<>(current, size,list.size());
//        PageUtil.pageImplent(page,current,size,list);
        return list;
    }
}
