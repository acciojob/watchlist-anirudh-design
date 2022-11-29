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
        List<Movie> movies;
        if(hm.size()==0){
            movies = new ArrayList<>();
        }
        else{
            movies = hm.get(null);
        }
        movies.add(movie);
        hm.put(null, movies);
    }

    public void addDirectorToDB(Director director){
        hm.put(director, new ArrayList<Movie>());
    }

    public void addMovieDirectorPairToDB(String movieName, String directorName){
        for(Director d:hm.keySet()){
            List<Movie> x=null;
            if(d!=null && d.getName().equals(directorName)) x =hm.get(d);
            List<Movie> y=hm.get(null);
            boolean flag=false;
            Movie m1=null;
            for(Movie m:y){
                if(m.getName().equals(movieName)){
                    m1=m;
                    flag=true;
                    x.add(m);
                    hm.put(d, x);
                }
            }
            if(flag){
                y.remove(m1);
                hm.put(null, y);
            }
        }
        if(hm.get(null).size()==0) hm.remove(null);
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
        for(Director d:new ArrayList<>(hm.keySet())){
            if(d!=null && d.getName().equals(directorName)){
                hm.remove(d);
            }
        }
    }

    public void deleteAllDirectorMoviesFromDB(){
        for(Director d:new ArrayList<>(hm.keySet())){
            if(d!=null) hm.remove(d);
        }
    }

}
