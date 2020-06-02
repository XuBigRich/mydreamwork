package cn.piao888.mydreamwork.util;

import java.util.Random;

public class BaseUtil {
    /**
     * 产生随机字符串
     * @param nub 所产生字符串 位数
     * @return  随机字符串
     */
    public static String random(int nub){
        String target="abcdefghijklmnopqrstuvwxyz1234567890";
        int length=target.length();
        Random r = new Random();
        int index=r.nextInt(length);
        StringBuffer randomString=new StringBuffer();
        for(int i=0;i<nub;i++){
           randomString.append(target.charAt(index));
        }
        return randomString.toString();
    }
}
