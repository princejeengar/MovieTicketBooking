package com.BookMyMovie.service.serviceImpl;

import com.BookMyMovie.dto.CinemaDTO;
import com.BookMyMovie.entity.Cinema;
import com.BookMyMovie.repository.CinemaRepo;
import com.BookMyMovie.service.CinemaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    CinemaRepo cinemaRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CinemaDTO createCinema(CinemaDTO cinemaDTO) {
        Cinema cinema = modelMapper.map(cinemaDTO, Cinema.class);
        Cinema savedCinema = cinemaRepo.save(cinema);
        return modelMapper.map(savedCinema, CinemaDTO.class);
    }

    @Override
    public List<CinemaDTO> getAllCinema() {
        List<Cinema> cinemas = cinemaRepo.findAll();
        return cinemas.stream().map(cinema -> modelMapper.map(cinema, CinemaDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void deleteCinema(Long cinemaId) {
        cinemaRepo.deleteById(cinemaId);
    }
}
