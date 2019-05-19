package com.bid.ykk.utils.shiro;

import com.bid.ykk.entity.User;
import com.bid.ykk.service.UserService;
import com.bid.ykk.utils.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user= (User) principals.getPrimaryPrincipal();
        if (user != null) {
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            info.addRole(user.getRole());
            //info.addStringPermissions(role.getPerName());
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();                //得到用户名
        String password = new String((char[])token.getCredentials());  //得到密码
        User userInfo=userService.findByUsername(username);
        if(userInfo==null)
        {
            System.out.println("用户不存在");
            throw new UnknownAccountException();
        }
        if(null != username && null != password){
            return new SimpleAuthenticationInfo(userInfo,PasswordHelper.encryptPassword(userInfo.getPassword()), getName());
        }else{
            return null;
        }
    }




}
