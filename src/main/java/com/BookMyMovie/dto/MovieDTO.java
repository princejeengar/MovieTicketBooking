package com.BookMyMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDTO {
    private Long movieId;
    private String movieName;
}