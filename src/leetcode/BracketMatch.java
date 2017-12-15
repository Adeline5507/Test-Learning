package leetcode;

import java.util.Stack;

public class BracketMatch {
    
    public static void main(String[] args){
        //match "{},(),[],<>"
        String testMatch ="{aaaaa<a>(c[bbb]a)}";
        String testNotMatch ="{aaaaa<a>(c[bbb]a)>}";
        System.out.println(isMatch(testMatch));
        System.out.println(isMatch(testNotMatch));
    }

    public static boolean isMatch(String test){
        Stack<Character> s = new Stack<Character>();
        for(int i=0;i<test.length();i++){
            if(test.charAt(i)=='{' || test.charAt(i)=='(' ||test.charAt(i)=='[' ||test.charAt(i)=='<' ){
                s.push(test.charAt(i));
            } else if(test.charAt(i)=='}' || test.charAt(i)==')' ||test.charAt(i)==']' ||test.charAt(i)=='>' ){
                if(s.empty()){
                    return false;
                }
                if((test.charAt(i)=='}' && s.peek()=='{') || (test.charAt(i)==')' && s.peek()=='(') || (test.charAt(i)==']' && s.peek()=='[')||(test.charAt(i)=='>' && s.peek()=='<')){
                    s.pop();
                } else {
                    return false;
                }
            }
            
            
        }
        
        if(!s.empty()){
            return false;
        } else{
            return true;
        }
        
    }
}
