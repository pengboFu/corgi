package com.x.contract;

/**
 * Created by fpb on 2018/12/17.
 */
public class ErrorCodes {
    /**
     * 服务器请求失败
     */
    public final static int SERVER_ERROR = 10001;
    /**
     * 密码错误
     */
    public final static int PASSWORD_ERROR = 10002;
    /**
     * 用户已经存在
     */
    public final static int ACCESS_IS_DENIED = 10003;

    /**
     * 用户不存在
     */
    public final static int USER_NOT_EXISTS = 10004 ;

    /**
     * 验证码无效
     */
    public final static int VERIFY_CODE_INVALID = 10005;
    /**
     * 用户已经在其他地方登录
     */
    public final static int USER_ALREADY_LOGGED_IN_ELSEWHERE=10006;
}
