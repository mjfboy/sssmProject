package com.oa.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 *@Author feri
 *@Date Created in 2018/7/9 14:31
 */
public class EncrypUtil2 {
    public static String salt="zeng";

    //md5摘要
	/**
       参数1：要加密的值
	   参数2：严密的盐值（生成的密码和该值有关系，该值不一致的话，密码也不会一样）
	   参数3：hash的次数
       
	*/
    public static String md5(String pass,String salt,int count){
        SimpleHash hash=new SimpleHash("md5",pass,salt,count);
        return hash.toString();
    }
    //md5摘要
    public static String md5Pass(String pass){
        SimpleHash hash=new SimpleHash("md5",pass,salt,128);
        return hash.toString();
    }


    public static void main(String[] args) {
        System.out.println(md5("admin",salt,128));
    }

}
