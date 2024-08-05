package com.morningstar.kill.pojo.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

import static com.morningstar.kill.pojo.vo.response.ResponseCode.ERROR;
import static com.morningstar.kill.pojo.vo.response.ResponseCode.SUCCESS;

/**
 * 返回数据类
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {
    //状态码
    private int code;
    //消息
    private String msg;
    //返回数据
    private T data;

    private R(int code){
        this.code = code;
    }
    private R(int code, T data){
        this.code = code;
        this.data = data;
    }
    private R(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    private R(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> ok(){
        return new R<T>(SUCCESS.getCode(),SUCCESS.getMessage());
    }
    public static <T> R<T> ok(String msg){
        return new R<T>(SUCCESS.getCode(), msg);
    }
    public static <T> R<T> ok(T data){
        return new R<T>(SUCCESS.getCode(), SUCCESS.getMessage(), data);
    }
    public static <T> R<T> ok(String msg, T data){
        return new R<T>(SUCCESS.getCode(), msg, data);
    }
    public static <T> R<T> ok(ResponseCode res, T data){
        return new R<T>(res.getCode(), res.getMessage(), data);
    }

    public static <T> R<T> error(){
        return new R<T>(ERROR.getCode(),ERROR.getMessage());
    }
    public static <T> R<T> error(String msg){
        return new R<T>(ERROR.getCode(), msg);
    }
    public static <T> R<T> error(ResponseCode res){
        return new R<T>(res.getCode(),res.getMessage());
    }
}