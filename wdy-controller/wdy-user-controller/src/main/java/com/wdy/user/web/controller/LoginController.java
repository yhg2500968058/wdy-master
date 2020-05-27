package com.wdy.user.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.user.model.dto.UserDTO;
import com.wdy.user.service.TbUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
 * @create: 2019-12-20 11:56
 */

@Controller
public class LoginController extends BaseController {

    @ApiOperation(value = "转发到登录页面")
    @GetMapping("/index")
    public String login() {
        return "index.html";
  }

    @GetMapping("/kkk")
    public String index() {
        return "index.html";
    }
    @GetMapping("l")
    public String l(){
        return "index";
    }



}
