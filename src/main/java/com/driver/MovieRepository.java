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

    public String addMovieToDB(Movie movie){
        List<Movie> movies;
        if(hm.size()==0){
            movies = new ArrayList<>();
        }
        else{
            for(Director d:hm.keySet()){
                List<Movie> x=hm.get(d);
                for(Movie m:x){
                    if(m.getName().equals(movie.getName())) return "Movie already there in the database";
                }
            }
            movies = hm.get(null);
        }
        movies.add(movie);
        hm.put(null, movies);
        return "Movie added successfully";
    }

    public String addDirectorToDB(Director director){
        if(hm.containsKey(director)) return "Director already added to the database";
        hm.put(director, new ArrayList<Movie>());
        return "Director added successfully to the database";
    }

    public String addMovieDirectorPairToDB(String movieName, String directorName){
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
        if(x.contains(m1)) return "Director-Movie pair already exists in the database";
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
        return "Director-Movie pair added successfully to the database";
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

    public String deleteDirectorMoviesFromDB(String directorName){
        boolean f=false;
        for(Director d:hm.keySet()){
            if(d!=null && d.getName().equals(directorName)){
                f=true;
                hm.remove(d);
                break;
            }
        }
        if(f) return "Director and his/her movies deleted from database";
        return "Director with given name does not exist in the database";
    }

    public String deleteAllDirectorMoviesFromDB(){
        if(hm.size()==0) return "The database is empty";
        for(Director d:hm.keySet()){
            if(d!=null) hm.remove(d);
        }
        return "All the directors and their movies deleted from the database";
    }

}
