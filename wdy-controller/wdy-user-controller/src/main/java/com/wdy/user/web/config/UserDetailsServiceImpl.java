package com.wdy.user.web.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.user.model.entity.TbUser;
import com.wdy.user.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/*
 * へ　　　　／|
 * 　　/＼7　　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿6
 *
 * @program: wdy
 * @description:
 * @author: yhg
 * @create: 2019-12-20 21:26
 */

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private TbUserService tbUserService;

    //认证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("认证");
        log.info("用户:{}开始登陆",username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        TbUser tbUser = tbUserService.findByLoginName(username);

        if (tbUser == null){
            log.info("用户:{}不存在",username);
            throw new BusinessException("用户不存在或密码错误！");
        }
        return new User(username,tbUser.getPassword(),grantedAuthoritySet);
    }
}
