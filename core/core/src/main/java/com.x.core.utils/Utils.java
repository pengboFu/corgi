package com.x.core.utils;

import com.x.core.contract.ErrorCodes;
import com.x.core.contract.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

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
    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    /**
     * 判断当前请求是否是微信webview请求。
     *
     * @param request
     * @return
     */
    public static boolean isInWX(HttpServletRequest request) {
        String ua = request.getHeader("user-agent");
        return ua.contains("MicroMessenger");
    }

    /**
     * 判断当前请求是否是支付宝webview请求。
     * @param request
     * @return
     */
    public static boolean isInAlipay(HttpServletRequest request) {
        String ua = request.getHeader("user-agent");
        return ua.contains("AlipayClient");
    }

    /**
     * 手机号格式化
     * @param mobilePhoneNumber
     * @param pattern
     * @return
     */
    public static String formatPhoneForShow(String mobilePhoneNumber, String pattern){
        if (Utils.isEmptyString(mobilePhoneNumber)) {
            return "";
        }
        char s[] = mobilePhoneNumber.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0,j = 0 ; i < s.length ; i++){
            sb.append(s[i]);
            if(i == 2 || i == 6){
                sb.append(pattern);
            }
        }
        return sb.toString();
    }

    /**
     * 获取本机所有IP
     *
     * @return map.key: 网卡名称, map.value: ip地址
     */
    public static Map<String, String> getAllLocalHostIP() {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces
                        .nextElement();
                System.out.println("---Name---:" + ni.getName());
                Enumeration nii = ni.getInetAddresses();
                while (nii.hasMoreElements()) {
                    ip = (InetAddress) nii.nextElement();
                    if (ip instanceof Inet6Address || ip.isLoopbackAddress()) {
                        continue;
                    }
                    res.put(ni.getName(), ip.getHostAddress());
                    System.out.println("本机的ip=" + ip.getHostAddress());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return res;
    }

}
