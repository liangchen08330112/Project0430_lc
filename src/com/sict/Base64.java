package com.sict;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1、先及那个字符串转换成二进制码
 * 2、再将二进制码六个为一组存到数组中
 * 3、将数组中的二进制转化成base64格式
 * 4、最后输出结果
 */
public class Base64 {
    public static void main(String[] args) {
        //提示
        System.out.println("请输入一个字符串：");
        //引入扫描器并接收用户输入
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        //转化为base64
        System.out.println(toBase64(str));
        System.out.println(new String(java.util.Base64.getEncoder().encode(str.getBytes())));
    }

    //将字符串转换成二进制
    public static String to2(String str){
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);
        String result = "";
        for(byte b : bytes){
            result += getBinaryStrFromByte(b);
        }
        System.out.println(result.length());
        if((result.length()%6)!=0){
            for(int i=0;i<(result.length()%6);i++){
                result+=0;
            }
        }
        return result;
    }
    public static String getBinaryStrFromByte(byte b){
        String result = "";
        byte a = b;
        for(int i=0;i<8;i++){
            byte c = a;
            a = (byte) (a>>1); //每移一位如同将10进制数除以2并去掉余数。
            a = (byte) (a<<1);
            if(a==c){
                result = "0"+result;
            }else{
                result = "1"+result;
            }
            a = (byte) (a>>1);
        }
        return result;
    }

    //将二进制以每六位储存
    public static List save(String str) {
        String result = to2(str);
        List<String> list = new ArrayList<>();
        for(int i=0;i<result.length();i+=6){
            list.add(result.substring(i,i+6));
        }
        return list;
    }

    //将十进制转换成base64
    public static String toBase64(String str){
        List<String> list = save(str);
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        String s = "";
        for(String l : list){
            Integer integer = Integer.parseInt(l,2);
            s = s+chars[integer];
        }
        return s;
    }
}
