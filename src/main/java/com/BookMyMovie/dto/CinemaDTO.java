package com.BookMyMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CinemaDTO {
    private Long cinemaId;
    private String cinemaName;
    private String cinemaAddress;
}
