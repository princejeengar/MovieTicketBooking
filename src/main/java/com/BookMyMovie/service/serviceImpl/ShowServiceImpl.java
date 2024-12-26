package com.BookMyMovie.service.serviceImpl;

import com.BookMyMovie.dto.CinemaDTO;
import com.BookMyMovie.dto.MovieDTO;
import com.BookMyMovie.dto.ShowDTO;
import com.BookMyMovie.entity.Cinema;
import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.entity.Show;
import com.BookMyMovie.repository.ShowRepo;
import com.BookMyMovie.service.CinemaService;
import com.BookMyMovie.service.MovieService;
import com.BookMyMovie.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private MovieService movieService;

    @Override
    public ShowDTO createShow(ShowDTO showDTO) {
        Show show = modelMapper.map(showDTO, Show.class);

        // Set Movie and Cinema IDs
        Movie movie = new Movie();
        movie.setMovieId(showDTO.getMovieId());
        show.setMovie(movie);

        Cinema cinema = new Cinema();
        cinema.setCinemaId(showDTO.getCinemaId());
        show.setCinema(cinema);

        // Save Show entity
        Show savedShow = showRepo.save(show);

        // Map back to ShowDTO and set movieId, cinemaId explicitly
        ShowDTO savedShowDTO = modelMapper.map(savedShow, ShowDTO.class);
        savedShowDTO.setMovieId(savedShow.getMovie().getMovieId());
        savedShowDTO.setCinemaId(savedShow.getCinema().getCinemaId());

        return savedShowDTO;
    }

    @Override
    public List<ShowDTO> getAllShows() {
        List<Show> shows = showRepo.findAll();
        return shows.stream()
                .map(show -> {
                    ShowDTO showDTO = modelMapper.map(show, ShowDTO.class);

                    // Explicitly set movieId and cinemaId
                    showDTO.setMovieId(show.getMovie().getMovieId());
                    showDTO.setCinemaId(show.getCinema().getCinemaId());

                    // Set full Movie and Cinema objects for detailed response
                    showDTO.setMovie(modelMapper.map(show.getMovie(), MovieDTO.class));
                    showDTO.setCinema(modelMapper.map(show.getCinema(), CinemaDTO.class));

                    return showDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteShow(Long showId) {
        showRepo.deleteById(showId);
    }

    @Override
    public Show getShowById(Long showId) {
        Optional<Show> showOptional = showRepo.findById(showId);
        if (showOptional.isPresent()) {
            return showOptional.get();
        } else {
            throw new IllegalArgumentException("No Show found with ID: " + showId);
        }
    }
}