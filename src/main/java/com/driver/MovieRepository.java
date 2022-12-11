package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<Director, List<Movie>> hm=new HashMap<>();

    public void addMovieToDB(Movie movie){
        List<Movie> movies;
        if(hm.size()==0){
            movies = new ArrayList<>();
        }
        else{
            for(Director d:hm.keySet()){
                List<Movie> x=hm.get(d);
                for(Movie m:x){
                    if(m.getName().equals(movie.getName())) return;
                }
            }
            movies = hm.get(null);
        }
        movies.add(movie);
        hm.put(null, movies);
    }

    public void addDirectorToDB(Director director){
        if(hm.containsKey(director)) return;
        hm.put(director, new ArrayList<Movie>());
    }

    public void addMovieDirectorPairToDB(String movieName, String directorName){
        List<Movie> x=null;
        for(Director d:hm.keySet()) {
            if (d != null && d.getName().equals(directorName)) {
                x = hm.get(d);
                break;
            }
        }
        Movie m1=null;
        List<Movie> movies=hm.get(null);
        for(Movie m:movies){
            if(m.getName().equals(movieName)) {
                m1 = m;
                break;
            }
        }
        if(m1==null) return;
        if(x.contains(m1)) return;
        List<Movie> y=hm.get(null);
        y.remove(m1);
        hm.put(null, y);
        if(hm.get(null).size()==0) hm.remove(null);
        if(!x.contains(m1)) x.add(m1);
        for(Director d:hm.keySet()){
            if (d != null && d.getName().equals(directorName)) hm.put(d, x);
        }
    }

    public Movie getMovieFromDB(String movieName){
        for(Director d:hm.keySet()){
            List<Movie> movies=hm.get(d);
            for(Movie m:movies) if(m.getName().equals(movieName)) return m;
        }
        return new Movie();
    }

    public Director getDirectorFromDB(String directorName){
        for(Director d:hm.keySet()) if(d!=null && d.getName().equals(directorName)) return d;
        return new Director();
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
        return res;
    }

    public List<String> getAllMoviesFromDB(){
        List<String> allMovies=new ArrayList<>();
        for(Director d:hm.keySet()){
            for(Movie m:hm.get(d)) allMovies.add(m.getName());
        }
        return allMovies;
    }

    public void deleteDirectorMoviesFromDB(String directorName){
        for(Director d:new ArrayList<>(hm.keySet())){
            if(d!=null && d.getName().equals(directorName)){
                hm.remove(d);
            }
        }
    }

    public void deleteAllDirectorMoviesFromDB(){
        if(hm.size()==0) return;
        for(Director d:new ArrayList<>(hm.keySet())){
            if(d!=null) hm.remove(d);
        }
    }

}
