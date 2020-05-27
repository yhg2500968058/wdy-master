/*
package com.wdy.biz.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdy.commons.util.wrapper.WrapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * User: yanghongguang
 * Date: 2020/3/17
 * Time: 16:32
 * Description:
 *//*

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //RedirectStrategy redirectStrategy1 = new DefaultRedirectStrategy();
        logger.info("登陆成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.ok())));
     //   HttpServletResponse redirectStrategy;
     //   redirectStrategy1.sendRedirect(request,response,"http://localhost:63342/wdy/wdy-master/wdy-ui/mbm/admin/index.html?_ijt=99ncp1pg9gon6226ri4qeumqio");
        String url = "http://localhost:63342/wdy/wdy-master/wdy-ui/mbm/admin/index.html";
        response.sendRedirect(url);
    }
}
*/
