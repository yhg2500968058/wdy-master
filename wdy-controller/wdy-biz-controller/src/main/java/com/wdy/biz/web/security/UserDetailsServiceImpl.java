/*
package com.wdy.biz.web.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.biz.model.entity.TbSeller;
import com.wdy.biz.service.TbSellerService;
import com.wdy.commons.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

*/
/**
 * User: yanghongguang
 * Date: 2020/3/17
 * Time: 16:53
 * Description:
 *//*

@Component("userDetailsServiceImpl")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private TbSellerService tbSellerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("商家开始登陆：[{}]", username);
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        TbSeller seller = this.tbSellerService.findByLoginName(username);
        if (seller == null) {
            log.info("商家不存在[{}]", username);
            throw new UsernameNotFoundException("用户名不存在或密码错误");
        }
        if (!seller.getStatus().equals("1")) {
            log.info("商家不可用：[{}]", username);
            throw new BusinessException("商家不可用");
        }
       // grantedAuthorities.add(new SimpleGrantedAuthority(seller.getSellerId()));
        return new SellerUserInfo(username, seller.getPassword(), seller.getName(), grantedAuthorities);
    }
}
*/
