package com.x.utils;

import com.x.contract.ErrorCodes;
import com.x.contract.ServiceException;

/**
 * Created by fpb on 2018/12/17.
 */
public class Utils {
    /**
     * 根据错误吗创建服务器异常实例，并从错误吗的注释中了解错误码所代表的含义
     * @param message 异常信息
     * @return ServiceException 服务异常实例
     */
    public static ServiceException getServiceException(String message){
        return getServiceException(ErrorCodes.SERVER_ERROR, message, null);
    }
    public static ServiceException getServiceException(int errCode,String message){
        return getServiceException(errCode,message,null);
    }
    public static ServiceException getServiceException(String message,Throwable throwable){
        return getServiceException(ErrorCodes.SERVER_ERROR,message,throwable);
    }
    public static ServiceException getServiceException(int errCode, String message, Throwable throwable) {
        return new ServiceException(message,errCode,throwable);
    }



}
