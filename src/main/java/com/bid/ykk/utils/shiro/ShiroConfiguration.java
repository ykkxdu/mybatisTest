package com.bid.ykk.utils.shiro;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfiguration {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // filterChainDefinitionMap.put("/details", "user");
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/wxinfo/wxLogin", "anon");
        filterChainDefinitionMap.put("/wxinfo/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/isLogin", "anon");
        filterChainDefinitionMap.put("/systemUserInfo/login", "anon");
        filterChainDefinitionMap.put("/userInfo/loginByUP", "anon");
        filterChainDefinitionMap.put("/userInfo/wxLogin", "anon");
        filterChainDefinitionMap.put("/tuokefiles/**", "anon");
        filterChainDefinitionMap.put("/websocket/devsocket", "anon");
        //过滤链条  map拦截
        filterChainDefinitionMap.put("/pay/wxPayNotify", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/index.html", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/fileController/uploadFile", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/area/getTree", "anon");
        filterChainDefinitionMap.put("/operateType/listAllname", "anon");
        filterChainDefinitionMap.put("/restaurantInfo/addRestaurantInfo", "anon");
        filterChainDefinitionMap.put("/restaurantInfo/selectRestaurantInfoById/**", "anon");
        //filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        Map<String , Filter> filterMap=new LinkedHashMap<String, Filter>();
        filterMap.put("authc",new MyShiroFilter());
        //shiroFilterFactoryBean.setLoginUrl("/unauth");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index.jsp");
        // 未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.jsp");
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }

    /**
     * Realm实现
     *
     * @return
     */
    @Bean
    public UserRealm ruserRealm() {
        UserRealm ruserRealm = new UserRealm();
       // ruserRealm.setCacheManager(cacheManager());
        ruserRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return ruserRealm;
    }





    @Bean
    public Collection<Realm> realms() {
        Collection<Realm> realms = new ArrayList<>();
        realms.add(ruserRealm());
        return realms;
    }

    @Bean
    public Map<String, Object> definedRealms(){
        Map<String,Object> realmMap=new HashMap<>();
        realmMap.put("RuserRealm",ruserRealm());
        return realmMap;
    }



    /**
     * 当只有一个Realm时，就使用这个Realm，当配置了多个Realm时，会使用所有配置的Realm。
     *
     * @return
     */
    @Bean
    MyModularRealmAuthenticator authenticator() {
        MyModularRealmAuthenticator authenticator = new MyModularRealmAuthenticator();
        authenticator.setDefinedRealms(definedRealms());
        return authenticator;
    }


    /**
     * 会话管理器
     *
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator());
        securityManager.setRealms(realms());
        //securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name = "sessionManager")
    public MyWebSessionManager sessionManager() {
        MyWebSessionManager sessionManager = new MyWebSessionManager();
        //sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    /**
     * Shiro生命周期处理器 ---可以自定的来调用配置在 Spring IOC 容器中 shiro bean 的生命周期方法.
     *
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }



    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }


}