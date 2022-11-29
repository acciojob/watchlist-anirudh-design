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
        movieService.addMovieService(movie);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){
        movieService.addDirectorService(director);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName){
        try {
            movieService.addMovieDirectorPairService(movieName, directorName);
        }catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        return new ResponseEntity<>(movieService.getMovieService(movieName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        return new ResponseEntity<>(movieService.getDirectorService(directorName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity<>(movieService.getMoviesListService(directorName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.getAllMoviesService(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName){
        movieService.deleteDirectorMoviesService(directorName);
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectorMoviesService();
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

}
