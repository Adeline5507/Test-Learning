package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Twitter2 {
    Map<Integer,List<Tweet>> tweets = new HashMap<Integer,List<Tweet>>();
    Map<Integer,HashSet<Integer>> relations = new HashMap<Integer,HashSet<Integer>>();
    int count = 0;
    public Twitter2() {
        
    }
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(tweets.get(userId)!=null){
            
            tweets.get(userId).add(new Tweet(tweetId,count++));
        } else {
            List<Tweet> tList = new ArrayList<Tweet>();
            tList.add(new Tweet(tweetId,count++));
            tweets.put(userId,tList);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> results = new ArrayList<Integer>();
        List<Tweet> temp = new ArrayList<Tweet>();
        if(relations.get(userId)!=null){
            for(Integer i:relations.get(userId)){
                    List<Tweet> ts = tweets.get(i);
                    if(ts!=null){
                        temp.addAll(ts);
                }
            }
        }
            
        List<Tweet> tt = tweets.get(userId);
        if(tt!=null){
            temp.addAll(tt);
        }
        
         Collections.sort(temp, new Comparator<Tweet>(){
            @Override
            public int compare(Tweet o1, Tweet o2) {
               if(o1.count>=o2.count){
                   return -1;
               }else {
                   return 1;
               }
            }
            
        });
         
        results = temp.stream().limit(10).map(t->new Integer(t.tweetId)).collect(Collectors.toList());  
        return results;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId!=followeeId){
            if(relations.get(followerId)!=null){
                relations.get(followerId).add(followeeId);
            } else {
                HashSet<Integer> rSet = new HashSet<Integer>();
                rSet.add(followeeId);
                relations.put(followerId,rSet);
            }
        }
    } 
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(relations.get(followerId)!=null){
            relations.get(followerId).remove(followeeId);
        } 
    }
    
    class Tweet{
        int tweetId;
        int count;
        
        public Tweet(int tweetId, int count) {
            this.tweetId = tweetId;
            this.count = count;
        }
    }
    
           
}
