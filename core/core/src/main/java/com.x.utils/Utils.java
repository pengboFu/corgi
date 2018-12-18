package com.x.utils;

import com.x.contract.ErrorCodes;
import com.x.contract.ServiceException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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
    /**
     * 判断当前BigDecimal值是否大于0
     * @param value
     * @return
     */
    public static Boolean isBigThanZero(BigDecimal value){
        if(value == null){
            return false;
        }
        if(value.doubleValue() > 0.0){
            return true;
        }
        return false;
    }

    /**
     * 是空的字符串
     * @param value
     * @return
     */
    public static Boolean isEmptyString(String value){
        return value == "" || value == null || value.length() <= 0;
    }
    /**
     * 不是空的字符串
     * @param value
     * @return
     */
    public static Boolean isNotEmptyString (String value){
        return !isEmptyString(value);
    }
    /**
     * 是空集合
     * @param collection
     * @param <E>
     * @return
     */
    public static <E> Boolean isEmptyCollection(Collection<E> collection){
        return collection == null || collection.size() == 0;
    }

    /**
     * 不是空集合
     * @param collection
     * @param <E>
     * @return
     */
    public static <E> Boolean isNotEmtyCollection(Collection<E> collection){
        return !isEmptyCollection(collection);
    }
    /**
     * 取出集合中第一个元素
     * @param collection
     * @param <E>
     * @return
     */
    public static <E> E firstCollection(Collection<E> collection){
        if(isEmptyCollection(collection)){
            return null;
        }else{
            return collection.iterator().next();
        }
    }
    /**
     * 是中国电话号码
     * @param phoneNumber
     * @return
     */
    public static Boolean isMobilePhoneInChina(String phoneNumber){
        if(isEmptyString(phoneNumber)){
            return false;
        }
        return phoneNumber.matches("^(13[0-9]|14[0-9]|16[0-9]|17[0-9]|15[0-9]|18[0-9]|19[0-9])\\d{8}$");
    }
    /**
     * 电话号码格式化
     * @param mobliePhoneNumber
     * @return
     */
    public static String encodeMobliePhoneNumber(String mobliePhoneNumber){
        if(isEmptyString(mobliePhoneNumber)){
            return "";
        }
        if(!isMobilePhoneInChina(mobliePhoneNumber)){
            return mobliePhoneNumber;
        }
        StringBuilder sb = new StringBuilder(mobliePhoneNumber);
        for (int i = 3; i <= 6; i++) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }
    /**
     * 忽略空集合添加集合
     * @param dest   目的集合
     * @param source 源集合
     * @param <T>    集合参数的类型
     */
    private static <T> void listAddAllAvoidNPE(List<T> dest, List<T> source) {
        if (source == null) {
            return;
        }
        dest.addAll(source);
    }
}
