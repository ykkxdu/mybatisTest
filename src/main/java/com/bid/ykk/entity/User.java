package com.bid.ykk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
@ApiModel("用户信息")
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "角色")
    @TableField("role")
    private String role;
    public User(){

    }

}
