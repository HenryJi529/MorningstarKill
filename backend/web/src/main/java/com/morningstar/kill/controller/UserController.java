package com.morningstar.kill.controller;

import com.morningstar.kill.pojo.entity.User;
import com.morningstar.kill.pojo.vo.request.LoginRequestVo;
import com.morningstar.kill.pojo.vo.response.LoginResponseVo;
import com.morningstar.kill.pojo.vo.response.R;
import com.morningstar.kill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name="用户认证相关接口定义")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     */
    @Operation(summary = "根据用户名查询用户信息")
    @Parameters({
            @Parameter(name = "username", description = "用户名", in = ParameterIn.PATH)
    })
    @GetMapping("/user/{username}")
    public R<User> getUserByUserName(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @Operation(summary = "用户登录功能")
    @PostMapping("/login")
    public R<LoginResponseVo> login(@Valid @RequestBody LoginRequestVo vo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return R.error(fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        }
        return this.userService.login(vo);
    }

    /**
     * 生成登录校验码的访问接口
     */
    @Operation(summary = "验证码生成功能")
    @GetMapping("/captcha")
    public R<Map<String, String>> getCaptchaCode(){
        return userService.getCaptchaCode();
    }
}
