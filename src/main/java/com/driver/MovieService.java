package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovieService(Movie movie){
        return movieRepository.addMovieToDB(movie);
    }

    public String addDirectorService(Director director){
        return movieRepository.addDirectorToDB(director);
    }

    public String addMovieDirectorPairService(String movieName, String directorName){
        return movieRepository.addMovieDirectorPairToDB(movieName, directorName);
    }

    public Movie getMovieService(String movieName){
        return movieRepository.getMovieFromDB(movieName);
    }

    public Director getDirectorService(String directorName){
        return movieRepository.getDirectorFromDB(directorName);
    }

    public List<String> getMoviesListService(String directorName){
        return movieRepository.getMoviesListFromDB(directorName);
    }

    public List<String> getAllMoviesService(){
        return movieRepository.getAllMoviesFromDB();
    }

    public String deleteDirectorMoviesService(String directorName){
        return movieRepository.deleteDirectorMoviesFromDB(directorName);
    }

    public String deleteAllDirectorMoviesService(){
        return movieRepository.deleteAllDirectorMoviesFromDB();
    }

}
