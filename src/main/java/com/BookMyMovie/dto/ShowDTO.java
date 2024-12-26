package com.BookMyMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowDTO {
    private Long showId;
    private String showTime;
    private Long movieId;
    private Long cinemaId;
    private MovieDTO movie;
    private CinemaDTO cinema;
}