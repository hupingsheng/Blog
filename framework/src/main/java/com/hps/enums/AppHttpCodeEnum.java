package com.hps.enums;


public enum AppHttpCodeEnum {

    //创建枚举成员   注意：枚举类成员必须写在成员属性和成员方法的前面
    //成功 code:200, message:操作成功
    SUCCESS(200,"操作成功"),

    //登录，注册相关异常枚举
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503,"邮箱已存在"),
    REQUIRE_USERNAME(504,"必须填用户名"),
    LOGIN_ERROR(505,"用户名或密码错误");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //由于是枚举，一般我们只需要得到值，而不需要set值
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
