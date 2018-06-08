package com.cic.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author daxian
 */
public class DaxianStringUtils extends StringUtils {
    /**
     * 字符串去重方法
     */
    public static String distinctStringWithSplit(String strArray,String regex){
        return  StringUtils.join(Stream.of(strArray.split(regex)).distinct().toArray(String[]::new),regex);
    }

    public static String distinctStringWithSplit(String strArray){
        if (strArray==null||strArray.length()==0){
            return "";
        }
        return  distinctStringWithSplit(strArray,",");
    }


    /**
     * 数字转指定长度字符串,不足的位数补0
     * @param num
     * @param length
     * @return
     */
    public static String getIntegerStringByLength(Integer num,Integer length){
        String format = "%0"+length+"d";
        return String.format(format, num);

    }

    /**
     * 将本地文件的路径，按照web访问地址的根路径，转换成web的访问地址
     * @param filePath
     * @param webRootPath
     * @param webRootPath
     * @return
     */
    public static String convertFilePathToWebPath(String filePath,String webRootPath,String webRootUrl){
        String relativePath =  removeFirst(filePath,webRootPath);
        return webRootUrl + relativePath;
    }

    /**
     * 数组array中是否包含str元素，包含返回true，不包含返回false
     * @param array
     * @param str
     * @return
     */
    public static boolean arrayContainsStr(String[] array,String str){
        return   Arrays.asList(array).contains(str);
    }

    public static String arrayToStringWithSplit(List<String> array, String split){
        StringBuffer sb = new StringBuffer();
        if(array!=null&&array.size()>0){
            array.forEach(str->sb.append(str+split));
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        List a = new ArrayList();
        a.add("999");
        a.add("888");

        System.out.println(arrayToStringWithSplit(a,","));
//       String[] aa = {"111","FFFF"};
//       String b = "FFFF";
//
//        System.out.println(arrayContainsStr(aa,b));






    }


}
