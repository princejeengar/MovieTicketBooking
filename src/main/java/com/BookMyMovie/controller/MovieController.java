package com.BookMyMovie.controller;

import com.BookMyMovie.dto.MovieDTO;
import com.BookMyMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        MovieDTO createdMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.ok(createdMovie);
    }

    @GetMapping("/Get-All")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/delete/movieId")
    public ResponseEntity<Void> deleteMovie(@RequestParam Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.noContent().build();
    }
}