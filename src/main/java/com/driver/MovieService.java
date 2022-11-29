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

    public void addMovieService(Movie movie){
        movieRepository.addMovieToDB(movie);
    }

    public void addDirectorService(Director director){
        movieRepository.addDirectorToDB(director);
    }

    public void addMovieDirectorPairService(String movieName, String directorName){
        movieRepository.addMovieDirectorPairToDB(movieName, directorName);
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

    public void deleteDirectorMoviesService(String directorName){
        movieRepository.deleteDirectorMoviesFromDB(directorName);
    }

    public void deleteAllDirectorMoviesService(){
        movieRepository.deleteAllDirectorMoviesFromDB();
    }

}
