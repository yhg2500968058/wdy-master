package com.wdy.user.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.user.model.dto.UserDTO;
import com.wdy.user.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
 * @create: 2019-12-21 16:38
 */

@RestController
@Api(tags = "用户API")
public class TbUserController extends BaseController {

    @Reference
    private TbUserService tbUserService;

    @PostMapping("/register")
    @ApiOperation(value = "注册用户",httpMethod = "POST")
    public Wrapper register(@ApiParam @RequestBody UserDTO userDTO){
        logger.info("注册用户：{}",userDTO);
        return this.tbUserService.register(userDTO);
    }

    @GetMapping("/me")
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    public Wrapper ma(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("获取用户信息:{}",authentication.getName());
        return WrapMapper.ok(authentication.getName());
    }
}


