package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args){
        String s = "abcdabcdabcd";
        Pattern p = Pattern.compile("(ab).*?" , Pattern.DOTALL);
        Matcher m = p.matcher(s);
        if(!m.find()){
            System.out.println("don't match");
        }
        System.out.println(m.group());
        System.out.println(m.group(1));
        //System.out.println(m.group(2));
        
        System.out.println("-----");
        
        m.reset();
        while(m.find()){
            System.out.println(m.group(1));
        }
    }
}
