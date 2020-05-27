package com.wdy.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.user.model.dto.UserDTO;
import com.wdy.user.model.entity.TbUser;
import org.springframework.stereotype.Service;

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
 * @create: 2019-12-21 12:07
 */

public interface TbUserService extends IService<TbUser> {


    /**
     * 更具用户名字查询用户信息
     * @param username
     * @return
     */
    TbUser findByLoginName(String username);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    Wrapper register(UserDTO userDTO);

    /**
     *
     * @param username
     * @return
     */
    Wrapper checkUsername(String username);
}
