package com.BookMyMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDTO {
    private Long seatId;
    private String seatNumber;
    private Boolean isAvailable;
    private Long showId;
    private ShowDTO showDTO;
}