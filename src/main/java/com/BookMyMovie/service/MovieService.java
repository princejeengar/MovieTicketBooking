package com.BookMyMovie.service;

import com.BookMyMovie.dto.MovieDTO;
import java.util.List;

public interface MovieService {

    MovieDTO createMovie(MovieDTO movieDTO);
    List<MovieDTO> getAllMovies();
    void deleteMovie(Long movieId);
}