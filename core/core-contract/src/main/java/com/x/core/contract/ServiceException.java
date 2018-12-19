package com.x.core.contract;

/**
 * Created by fpb on 2018/12/17.
 */
public class ServiceException extends RuntimeException {

    private int errorCode = 10000;

    public ServiceException(String message,int errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(int errorCode){
        this.errorCode = errorCode;
    }

    public ServiceException(String message,int errorCode,Throwable cause){
        super(message,cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode(){
        return errorCode;
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }
}
