package com.wdy.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.wdy.task.service.SmsService;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @program: lg-master
 * @description:
 * @author: 徐子楼
 * @create: 2019-03-05 10:29
 **/
@RestController
@RequestMapping("/sms")
public class SmsController {


   /* @Autowired
    private SmsService smsService;


    @GetMapping("/register/{phone}")
    public Wrapper sendVerificationCode(@PathVariable String phone) {
        this.smsService.sendVerificationCode(phone);
        return WrapMapper.ok();
    }

    @GetMapping("/checkVerificationCode")
    public Wrapper checkVerificationCode(String phone, String code) {
        Boolean flag = this.smsService.checkVerificationCode(phone, code);
        if (!flag) {
            return WrapMapper.error("验证码错误!或者过期请重试");
        }
        return WrapMapper.ok();
    }*/
}
