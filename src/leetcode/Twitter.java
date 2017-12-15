package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Twitter {


    /** Initialize your data structure here. */
    List<User> users;
    List<Post> posts;
    public Twitter() {
        users = new ArrayList<User>();
        posts = new ArrayList<Post>();
        
        LinkedList<Integer> nodes = new LinkedList<Integer>();
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Post newPost = new Post(userId,tweetId,new Date().getTime()); 
        posts.add(newPost);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User user = users.stream().filter(u -> u.userId==userId).findAny().orElse(null);
        return posts.stream().filter(p ->(p.userId == userId || ((user!=null)&&user.followeeId.contains(new Integer(p.userId))))).sorted(new Comparator<Post>(){
            @Override
            public int compare(Post o1, Post o2) {
                System.out.println("----"+o1.postTime+","+o2.postTime);
                if(o1.postTime >= o2.postTime){
                    return -1;
                } else {
                    return 1;
                }
            }
            
        }).map(p -> p.postId).limit(10).collect(Collectors.toList());
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User user = users.stream().filter(u -> u.userId==followerId).findAny().orElse(null);
        if(user == null){
            user = new User(followerId);
            users.add(user);
        } 
        
        user.followeeId.add(followeeId);
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User user = users.stream().filter(u -> (u.userId==followerId)).findAny().orElse(null);
        if(user !=null){
            user.followeeId.remove(followeeId);
        }
    }
    
    class User{
        int userId;
        HashSet<Integer> followeeId;
        public User(int userId){
            this.userId = userId;
            this.followeeId = new HashSet<Integer>();
        }
    }
    
    class Post{
        int userId;
        int postId;
        long postTime;
        
        public Post(int userId, int postId, long time) {
            this.userId = userId;
            this.postId = postId;
            this.postTime = time;
        }
    }

}
