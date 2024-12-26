package com.BookMyMovie.service.serviceImpl;

import com.BookMyMovie.dto.MovieDTO;
import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.repository.MovieRepo;
import com.BookMyMovie.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private final MovieRepo movieRepo;

    @Autowired
    private final ModelMapper modelMapper;


    public MovieServiceImpl(MovieRepo movieRepo, ModelMapper modelMapper) {
        this.movieRepo = movieRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie savedMovie = movieRepo.save(movie);
        return modelMapper.map(savedMovie, MovieDTO.class);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return movies.stream().map( movie -> modelMapper.map(movie, MovieDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void deleteMovie(Long movieId) {
        movieRepo.deleteById(movieId);
    }
}