/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: WebSecurityConfig
 */
package com.kiritor.hshop.serviceuser.security.config;

import com.kiritor.hshop.serviceuser.dao.UserDao;
import com.kiritor.hshop.serviceuser.security.Interceptor.AuthenticationInterceptor;
import com.kiritor.hshop.serviceuser.security.filter.CustomAuthenticationFilter;
import com.kiritor.hshop.serviceuser.security.filter.JWTAuthenticationFilter;
import com.kiritor.hshop.serviceuser.security.handler.LoginExpireHandler;
import com.kiritor.hshop.serviceuser.security.handler.LoginSuccessHandler;
import com.kiritor.hshop.serviceuser.security.handler.LogoutHandler;
import com.kiritor.hshop.serviceuser.security.handler.MyAuthLimitHandler;
import com.kiritor.hshop.serviceuser.security.handler.UrlRoleAuthHandler;
import com.kiritor.hshop.serviceuser.security.handler.UrlRolesFilterHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class WebSecurityConfig
 * @Description
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired(required = false)
    private UserDao userDao;

    /**
     * 用户密码校验过滤器,自定义用户密码获取方案
     */
    private final CustomAuthenticationFilter customAuthenticationFilter;


    public WebSecurityConfig(CustomAuthenticationFilter customAuthenticationProcessingFilter) {
        this.customAuthenticationFilter = customAuthenticationProcessingFilter;
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /* 开启跨域共享，  跨域伪造请求限制=无效 */
        http.cors().and().csrf().disable();

        /**
         * 设置无session状态
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        /* 开启授权认证 */
        http.authorizeRequests()
                //需要认证
                .antMatchers("/v1/users/").authenticated()
                /* 动态url权限 */
                .withObjectPostProcessor(new DefinedObjectPostProcessor())
                /* url决策 */
                .accessDecisionManager(accessDecisionManager())
                .antMatchers("/login").permitAll()
                //其他资源放行
                .anyRequest().permitAll();

        /* 登录配置 */
        http.formLogin().usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");

        /* 认证失败处理(自定义了认证流程，所以无需指定了) */
        //http.formLogin().failureHandler(new MyAuthenticationFailureHandler());

        /* 登录过期/未登录 处理 */
        http.exceptionHandling().authenticationEntryPoint(new LoginExpireHandler());
        /* 权限不(没有赋予角色) 处理 */
        http.exceptionHandling().accessDeniedHandler(new MyAuthLimitHandler());

        /* 登录成功后的处理 */
        http.formLogin().successHandler(new LoginSuccessHandler());

        /* 退出成功处理器 */
        http.logout().logoutUrl("/logout").permitAll()
                .invalidateHttpSession(true)
                .logoutSuccessHandler(new LogoutHandler());

        /**
         * 配置token验证过滤器
         */
        http.addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        /**
         * 自定义用户密码获取方案
         */
        http.addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 自定义方法
     * @return
     */

    /*
    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            if (username==null||username.trim().length()<=0) {
                throw new UsernameNotFoundException("用户名为空");
            }

            User sysUser = userDao.selectByUserName(username);
            if(sysUser!=null){
                return sysUser;
            }

            throw new UsernameNotFoundException("用户不存在!");
        };
    }
    */


    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                    // 拦截所有请求，通过判断是否有 @PassToken 注解跳过token认证,默认需要经过
                    registry.addInterceptor(new AuthenticationInterceptor())
                               .addPathPatterns("/**");
            }
        };
    }


    /**
     * AffirmativeBased – 任何一个AccessDecisionVoter返回同意则允许访问
     * ConsensusBased – 同意投票多于拒绝投票（忽略弃权回答）则允许访问
     * UnanimousBased – 每个投票者选择弃权或同意则允许访问
     *
     * 决策管理
     */
    private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new WebExpressionVoter());
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new RoleVoter());
        /* 路由权限管理 */
        decisionVoters.add(new UrlRoleAuthHandler());
        return new UnanimousBased(decisionVoters);
    }


    @Autowired
    private UrlRolesFilterHandler urlRolesFilterHandler;


    class DefinedObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
            object.setSecurityMetadataSource(urlRolesFilterHandler);
            return object;
        }
    }
}



