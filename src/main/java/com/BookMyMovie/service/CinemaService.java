package com.BookMyMovie.service;

import com.BookMyMovie.dto.CinemaDTO;
import java.util.List;

public interface CinemaService {
    CinemaDTO createCinema(CinemaDTO cinemaDTO);
    List<CinemaDTO> getAllCinema();
    void deleteCinema(Long cinemaId);
}