package com.morningstar.kill.pojo.vo.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "验证码响应对象")
public class CaptchaResponseVo {
    @Schema(description = "会话标识")
    private String sessionId;
    @Schema(description = "验证码图片数据")
    private String imageData;
}