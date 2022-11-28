package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
public class MovieRepository {
    Map<Director, List<Movie>> hm=new HashMap<>();

    public void addMovieToDB(Movie movie){
        if(hm.size()==0){
            List<Movie> movies=new ArrayList<>();
            movies.add(movie);
            hm.put(null, movies);
        }
        else{
            List<Movie> movies=hm.get(null);
            movies.add(movie);
            hm.put(null, movies);
        }
    }

    public void addDirectorToDB(Director director){
        hm.put(director, new ArrayList<Movie>());
    }

    public void addMovieDirectorPairToDB(String movieName, String directorName){
        List<Movie> x=null;
        for(Director d:hm.keySet()){
            if(d!=null && d.getName().equals(directorName)) {
                x=hm.get(d);
                break;
            }
        }
        Movie m1=null;
        for(Director d:hm.keySet()){
            List<Movie> movies=hm.get(d);
            for(Movie m:movies){
                if(m.getName().equals(movieName)){
                    m1=m;
                    break;
                }
            }
        }
        for(Director d:hm.keySet()){
            List<Movie> movies=hm.get(d);
            for(Movie m:movies){
                if(m==m1){
                    movies.remove(m);
                    hm.put(d, movies);
                    break;
                }
            }
        }
        if(hm.get(null).size()==0) hm.remove(null);
        if(!x.contains(m1)) x.add(m1);
    }

    public Movie getMovieFromDB(String movieName){
        for(Director d:hm.keySet()){
            List<Movie> movies=hm.get(d);
            for(Movie m:movies) if(m.getName().equals(movieName)) return m;
        }
        return null;
    }

    public Director getDirectorFromDB(String directorName){
        for(Director d:hm.keySet()) if(d!=null && d.getName().equals(directorName)) return d;
        return null;
    }

    public List<String> getMoviesListFromDB(String directorName){
        List<String> res=new ArrayList<>();
        for(Director d:hm.keySet()){
            if(d!=null && d.getName().equals(directorName)){
                List<Movie> movies=hm.get(d);
                for(Movie m:movies) res.add(m.getName());
                break;
            }
        }
        return null;
    }

    public List<String> getAllMoviesFromDB(){
        List<String> allMovies=new ArrayList<>();
        for(Director d:hm.keySet()){
            for(Movie m:hm.get(d)) allMovies.add(m.getName());
        }
        return allMovies;
    }

    public void deleteDirectorMoviesFromDB(String directorName){
        for(Director d:hm.keySet()){
            if(d!=null && d.getName().equals(directorName)){
                hm.remove(d);
                break;
            }
        }
    }

    public void deleteAllDirectorMoviesFromDB(){
        for(Director d:hm.keySet()){
            if(d!=null) hm.remove(d);
        }
    }

}
