package com.morningstar.kill.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.morningstar.kill.constant.CaptchaConstant;
import com.morningstar.kill.mapper.UserMapper;
import com.morningstar.kill.util.IdWorker;
import com.morningstar.kill.pojo.entity.User;
import com.morningstar.kill.pojo.vo.request.LoginRequestVo;
import com.morningstar.kill.pojo.vo.response.LoginResponseVo;
import com.morningstar.kill.pojo.vo.response.R;
import com.morningstar.kill.pojo.vo.response.ResponseCode;
import com.morningstar.kill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public R<User> getUserByUsername(String username) {
        User dbUser = userMapper.selectByUsername(username);
        if (dbUser == null) {
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        return R.ok(dbUser);
    }

    @Override
    public R<LoginResponseVo> login(LoginRequestVo vo) {
        // 判断数据格式是否有误
        if(vo == null
                || StringUtils.isBlank(vo.getSessionId())
                || StringUtils.isBlank(vo.getUsername())
                || StringUtils.isBlank(vo.getPassword())
                || StringUtils.isBlank(vo.getCode())) {
            return R.error(ResponseCode.DATA_ERROR);
        }

        // 从redis中获取缓存的校验码
        String redisCheckCode = redisTemplate.opsForValue().get(CaptchaConstant.CHECK_PREFIX + vo.getSessionId());
        // 判断获取的验证码是否存在，以及是否与输入的验证码相同
        if (StringUtils.isBlank(redisCheckCode)){
            return R.error(ResponseCode.CHECK_CODE_TIMEOUT);
        }
        if(!redisCheckCode.equalsIgnoreCase(vo.getCode())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }

        // 根据用户名查询用户信息
        User dbUser = userMapper.selectByUsername(vo.getUsername());
        // 判断用户是否存在，存在则密码校验比对
        if (dbUser == null){
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        if(!passwordEncoder.matches(vo.getPassword(),dbUser.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }

        // 组装登录成功数据
        LoginResponseVo respVo = new LoginResponseVo();
        // 属性名称与类型必须相同，否则属性值无法copy
        BeanUtils.copyProperties(dbUser, respVo);
        return R.ok(respVo);
    }

    @Override
    public R<Map<String, String>> getCaptchaCode() {
        // 参数分别是宽、高、验证码长度、干扰线数量
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        // 设置背景颜色清灰
        captcha.setBackground(Color.LIGHT_GRAY);
        // 自定义校验码生成方式
//        captcha.setGenerator(new CodeGenerator() {
//            @Override
//            public String generate() {
//                return RandomStringUtils.randomNumeric(4);
//            }
//            @Override
//            public boolean verify(String code, String userInputCode) {
//                return code.equalsIgnoreCase(userInputCode);
//            }
//        });
        // 获取图片中的验证码，默认生成的校验码包含文字和数字，长度为4
        String checkCode = captcha.getCode();
        log.info("生成校验码: {}", checkCode);

        // 生成sessionId
        String sessionId = Long.toHexString(idWorker.nextId());
        // 将sessionId和校验码保存在redis下，并设置缓存中数据存活时间一分钟
        redisTemplate.opsForValue().set(CaptchaConstant.CHECK_PREFIX +sessionId, checkCode,1, TimeUnit.MINUTES);

        // 组装响应数据
        Map<String, String> info = new HashMap<>();
        info.put("sessionId", sessionId);
        info.put("imageData", captcha.getImageBase64()); // 获取base64格式的图片数据
        // 设置响应数据格式
        return R.ok(info);
    }
}
