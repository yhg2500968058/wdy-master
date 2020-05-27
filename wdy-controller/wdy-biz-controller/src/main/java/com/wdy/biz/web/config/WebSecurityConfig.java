/*
package com.wdy.biz.web.config;

import com.wdy.biz.web.security.AuthenticationAccessDeniedHandler;
import com.wdy.biz.web.security.AuthenticationFailureHandler;
import com.wdy.biz.web.security.AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

*/
/**
 * User: yanghongguang
 * Date: 2020/3/17
 * Time: 15:52
 * Description:
 *//*

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter  {
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

   */
/* @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(){
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter= new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.
    }*//*


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        //System.out.println(bCryptPasswordEncoder.matches("",bCryptPasswordEncoder.encode("123456")));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .cacheControl()
                .and()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/sellerAudit/**").authenticated()
                .antMatchers("/sellerInfo").authenticated()
                .antMatchers("/tbGoods/**").authenticated()
                .antMatchers("/tbItemCats/**").authenticated()
                .antMatchers("/tbSellers/**").authenticated()
                .antMatchers("/tbSellers/register").permitAll()
                .antMatchers("/tbTypeTemplate/**").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("http://localhost:63342/wdy/wdy-master/wdy-ui/mbm/shoplogin.html")
              //  .defaultSuccessUrl("http://localhost/mbm/admin/index.html")
                .loginProcessingUrl("/login")
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(authenticationAccessDeniedHandler);

        http.authorizeRequests()
                .antMatchers("/sellerAudit/**").authenticated()
                .antMatchers("/tbSeller/**").authenticated()
                .antMatchers("/tbGoods/**").authenticated()
                .antMatchers("/tbItemCats/**").authenticated()
                .antMatchers("/tbSellers/**").authenticated()
                .antMatchers("/tbSellers/register").permitAll()
                .antMatchers("/tbTypeTemplate/**").authenticated();
    }

    */
/**
     * 忽略拦截
     * @param web
     * @throws Exception
     *//*

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略url - 会直接过滤该url - 将不会经过Spring Security过滤器链
       // web.ignoring().antMatchers("/sellerInfo");
        // 设置拦截忽略文件夹，可以对静态资源放行
       // web.ignoring().antMatchers("/css/**", "/js/**");
    }
}
*/
