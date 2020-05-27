package com.wdy.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.user.mapper.TbUserMapper;
import com.wdy.user.model.dto.UserDTO;
import com.wdy.user.model.entity.TbUser;
import com.wdy.user.service.TbUserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
 * @create: 2019-12-21 12:17
 */

@Service(version = "1.0.0", timeout = 10000)
@Transactional
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper,TbUser> implements TbUserService {

    @Override
    public TbUser findByLoginName(String username) {
        if (StrUtil.isBlank(username)){
            throw new BusinessException("用户名为空");
        }
        System.out.println("jinglaile");
        return this.baseMapper.selectOne(new QueryWrapper<TbUser>().eq("username",username));
    }

    @Override
    public Wrapper register(UserDTO userDTO) {
        if (userDTO == null){
            return WrapMapper.error("注册失败");
        }
        if (!checkUsername(userDTO.getUsername()).success()){
            throw new BusinessException(500 ,"用户名已存在请您重新输入！");
        }
        TbUser tbUser = new TbUser();
        BeanUtil.copyProperties(userDTO,tbUser);
        tbUser.setCreated(LocalDateTime.now());
        tbUser.setUpdated(LocalDateTime.now());
        this.baseMapper.insert(tbUser);
        return WrapMapper.ok();
    }

    @Override
    public Wrapper checkUsername(String username) {
        if (StrUtil.isBlank(username)){
            throw new BusinessException("用户名为空！");
        }
        TbUser tbUser = this.baseMapper.selectOne(new QueryWrapper<TbUser>().eq("username",username));
        if (tbUser == null) {
            return WrapMapper.ok();
        }
        return WrapMapper.wrap(500,"用户名已存在请您重新输入！");
    }
}
