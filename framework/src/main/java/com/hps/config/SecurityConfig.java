package com.hps.config;


import com.hps.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //这个配置用于登录时认证，只有使用了这个配置才能自动注入AuthenticationManager，并使用他来进行用户认证
    //将AuthenticationManager注入到Spring容器中
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //自定义登录校验过滤器，检查token并更新到上下文
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //自定义登陆失败异常处理器
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                //测试友链接口需要登录后才能访问
                .antMatchers("/link/getAllLink").authenticated()
                .anyRequest().permitAll();

        http.logout().disable();

        //配置异常处理器
        //1.认证失败异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);

        //将jwtAuthenticationTokenFilter自定义的过滤器配置到UsernamePasswordAuthenticationFilter过滤器之前
        http.addFilterBefore(jwtAuthenticationTokenFilter,
                UsernamePasswordAuthenticationFilter.class);

        http.cors();

    }



}
