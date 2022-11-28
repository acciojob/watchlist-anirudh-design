package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){
        try {
            movieService.addMovieService(movie);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){
        try {
            movieService.addDirectorService(director);
        }catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName){
        try {
            movieService.addMovieDirectorPairService(movieName, directorName);
        }catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Director-Movie pair added to database successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        return new ResponseEntity<>(movieService.getMovieService(movieName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        return new ResponseEntity<>(movieService.getDirectorService(directorName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity<>(movieService.getMoviesListService(directorName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.getAllMoviesService(), HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName){
        try {
            movieService.deleteDirectorMoviesService(directorName);
        }catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Director and his/her movies deleted from database", HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        try {
            movieService.deleteAllDirectorMoviesService();
        }catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("All the directors and their movies deleted from the database", HttpStatus.OK);
    }

}
