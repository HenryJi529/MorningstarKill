package com.morningstar.kill.pojo.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestVo {
    /**
     * sessionId
     */
    private String sessionId;

    /**
     * 用户名
     */
    @Size(min = 5, max = 32, message = "用户名最短为5个字符，最长为32个字符")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;
}
