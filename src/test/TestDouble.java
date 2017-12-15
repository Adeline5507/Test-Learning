package test;

import java.util.Date;

public class TestDouble {
    
    public static void main(String[] args){
        Double test = Double.NaN;
        System.out.println("----"+(test == Double.NaN));
        System.out.println("----"+(test.isNaN()));
        System.out.println("----test:"+test);
        
        Integer sequence = Integer.MIN_VALUE;
        System.out.println("----"+(sequence ==Integer.MIN_VALUE));
        
        Date tDate = new Date(1487259591223L);
        System.out.println(tDate);
        
        
        int s = 1;
        switch(s){
            case 1:
            case 2:
                System.out.println("hahahah");
        
        }
    }

}
