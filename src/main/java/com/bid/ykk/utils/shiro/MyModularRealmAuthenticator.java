package com.bid.ykk.utils.shiro;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;
import java.util.Map;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    private Map<String, Object> definedRealms;

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }


    @Override
    protected void assertRealmsConfigured() throws IllegalStateException {
        super.assertRealmsConfigured();
    }

    @Override
    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {
        // 如果该realms不支持(不能验证)当前token
        if (!realm.supports(token)) {
            throw new ShiroException("token错误!");
        }
        AuthenticationInfo info = null;

            info = realm.getAuthenticationInfo(token);

            if (info == null) {
                throw new ShiroException("token不存在!");
            }
        return info;
    }

    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
        this.assertRealmsConfigured();
        Realm realm = null;

        if (realm == null) {
            return null;
        }
        return this.doSingleRealmAuthentication(realm, token);

    }

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        return super.doAuthenticate(authenticationToken);
    }
}
