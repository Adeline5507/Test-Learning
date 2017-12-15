package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MovieRecommandation {
   
    public  static void main(String[] args){
        //录入电影
        Movie m1 = new Movie(1,4.0f);
        Movie m2 = new Movie(2,3.0f);
        Movie m3 = new Movie(3,4.8f);
        Movie m4 = new Movie(4,3.9f);
        m1.addRelatedMovie(m2);
        m1.addRelatedMovie(m3);
        m2.addRelatedMovie(m4);
        m3.addRelatedMovie(m4);
        //获得与一个电影相关的推荐电影,取评分高的前N个
        
        Set<Movie> sMovie = Movie.getRecommandMovies(m3,2);
        for(Movie m:sMovie){
            System.out.println(m.id+" "+m.rating);
        }
    }
}


class Movie{
    int id;
    float rating;
    HashSet<Movie> relatedMovies;
    
    public Movie(int id,float rating){
        this.id = id;
        this.rating = rating;
        relatedMovies = new HashSet<Movie>();
        
    }
    
    public HashSet<Movie> getRelatedMovies(){
        return relatedMovies;
    }
    
    public void addRelatedMovie(Movie m){
        this.relatedMovies.add(m);
        m.getRelatedMovies().add(this);
    }
    
    public static Set<Movie> getRecommandMovies(Movie m,int n){
        Set<Movie> mSet = new HashSet<Movie>();
        Set<Movie> visited = new HashSet<Movie>();
        for(Movie m1:m.relatedMovies){
            Set<Movie> s = getSub(m1,m,visited);
            if(s!=null){
                mSet.addAll(s);
            }
        }
        
        List<Movie> list = new ArrayList<Movie>(mSet);
        Collections.sort(list, new Comparator<Movie>(){
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.rating > o2.rating){
                    return -1;
                }else if(o1.rating == o2.rating){
                    return 0;
                }else{
                    return 1;
                }
            }
            
        });
        mSet.clear();
        if(n>list.size()){
            n = list.size();
        }
        
        for(int i=0;i<n;i++){
            mSet.add(list.get(i));
        }
        
        return mSet;
    }
    
    public static Set<Movie> getSub(Movie m,Movie o,Set<Movie> visited){
        if(visited.contains(m)){
            return null;
        }
        
        Set<Movie> s = new HashSet<Movie>();
        if(!m.equals(o)){
            visited.add(m);
            s.add(m);
        }
        
        for(Movie m1:m.relatedMovies){
            Set<Movie> result = getSub(m1,o,visited);
            if(result == null){
                continue;
            }
            s.addAll(result);
            
        }
        return s;
    }
    
    public boolean equals(Object anObject){
        if(anObject instanceof Movie && ((Movie) anObject).id == this.id){
            return true;
        }
        return false;
    }
    
    public int hashCode(){
        return this.id;
    }
   
}