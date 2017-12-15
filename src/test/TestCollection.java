package test;

import java.util.ArrayList;
import java.util.List;

public class TestCollection {
    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        for (String temp : a) {
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
        
        for(String t:a){
            System.out.println(t);
        }
    }
}
