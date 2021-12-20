package com.itheima.security_demo.config;

import com.itheima.security_demo.service.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)//开启方法注解的权限控制

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/index.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()//设置表单登录
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler())
                .and().csrf().disable();
    }

    @Autowired
    private MyUserServiceImpl myUserService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }

    public static void main(String[] args) {
        String encode=new BCryptPasswordEncoder().encode("1234");
        System.out.println(encode);
    }
}
