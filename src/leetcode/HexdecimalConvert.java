package leetcode;

import java.util.Stack;

public class HexdecimalConvert {
    public static void main(String[] args){
        Integer n = -7;
        //转换成二进制
        //System.out.println(toBinary(n));
        
        System.out.println(Integer.toBinaryString(0x33ffffff));
        System.out.println(Integer.toBinaryString(0x33ffffff & 0xff000000));
        System.out.println(Integer.toBinaryString(0x33ffffff & 0xff000000 >>> 1));
        System.out.println(Integer.toBinaryString(0x03ffffff & 0xff000000 >>> 2));
        //二进制转换为十六进制
        
        System.out.println("1:"+"2001.0".replace(".0", ""));
        System.out.println("2:"+"18.0".replaceAll(".0", ""));

    }
    
    

    public static String positiveNumToBinary(int n){
        String r = "";
        while(n>1){
            r =  (n % 2) + r;
            n = n/2;
        }
       
        r = 1+r;
        return r;
    }
    
    public static String toBinary(int n){
        int m = Math.abs(n);
        int i = 31;
        int[] r = new int[32];
        while(m>1){
            r[i] = (m % 2);
            m = m/2;
            i--;
        }
        r[i]=1;
        
        if(n<0){
            for(int j=0;j<32;j++){
                r[j] = r[j]==1?0:1;
            }
            int j=31;
            while(j>=0 && (r[j] == 1)){
                r[j]=0;
                j--;
            }
            r[j]=1;
        }
        String res = "";
        for(int j=0;j<32;j++){
            res += r[j];
        }
        return res;
    }
}
