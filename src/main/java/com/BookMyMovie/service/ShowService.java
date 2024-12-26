package com.BookMyMovie.service;

import com.BookMyMovie.dto.ShowDTO;
import com.BookMyMovie.entity.Show;

import java.util.List;

public interface ShowService {
    ShowDTO createShow(ShowDTO showDTO);
    List<ShowDTO> getAllShows();
    void deleteShow(Long showId);
    Show getShowById(Long showId);
}
