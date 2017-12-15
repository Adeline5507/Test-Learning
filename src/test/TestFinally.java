package test;

public class TestFinally {
    
    public String test(char t) throws Exception{
        String tt = null;
        try{
            switch(t){
             case 'a' : tt = "a"; break;
             case 'b' : tt = "b"; break;
             case 'c' : tt = "c"; break;
            }
            throw new Exception("exce");
        }finally{
            return tt; //not good
        }
       
    }
    public static void main(String[] args) throws Exception{
        TestFinally t = new TestFinally();
        System.out.println(t.test('a'));
    }
}
