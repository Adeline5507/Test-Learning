package leetcode;

import java.util.HashMap;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s==null || t==null || t.length()!=s.length()){
            return false;
        }else if(t.equals(s)){
            return true;
        }else{
            HashMap<Character,Integer> sMap = new HashMap<Character,Integer>();
            HashMap<Character,Integer> tMap = new HashMap<Character,Integer>();
            
            for(int i=0;i<t.length();i++){
                char tc = t.charAt(i);
                if(tMap.containsKey(tc)){
                    tMap.put(tc, tMap.get(tc)+1);
                } else {
                    tMap.put(tc, 0);
                }
                
                char sc = s.charAt(i);
                if(sMap.containsKey(sc)){
                    sMap.put(sc, sMap.get(sc)+1);
                } else {
                    sMap.put(sc, 0);
                }
            }
            
            if(tMap.size()!=sMap.size()){
                return false;
            } else {
                for(Character key:tMap.keySet()){
                    if(sMap.get(key).intValue()!=tMap.get(key).intValue()){
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public boolean isAnagram2(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    
    public static void main(String[] args) throws Exception{
        ValidAnagram t = new ValidAnagram();
        System.out.println(t.isAnagram("anagram", "nagaram"));
        System.out.println(t.isAnagram("rat", "cat"));
        System.out.println(t.isAnagram("aabbcc", "baabcc"));
        System.out.println(t.isAnagram("aaab", "aabc"));
        System.out.println(t.isAnagram("aabc", "aaab"));
        System.out.println(t.isAnagram("aacc", "aaca"));
    }
}
