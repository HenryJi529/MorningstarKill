package com.morningstar.kill.service;

import com.morningstar.kill.pojo.entity.User;
import com.morningstar.kill.pojo.vo.request.LoginRequestVo;
import com.morningstar.kill.pojo.vo.response.LoginResponseVo;
import com.morningstar.kill.pojo.vo.response.R;

import java.util.Map;

public interface UserService {
    R<User> getUserByUsername(String username);

    R<LoginResponseVo> login(LoginRequestVo vo);

    R<Map<String, String>> getCaptchaCode();
}
