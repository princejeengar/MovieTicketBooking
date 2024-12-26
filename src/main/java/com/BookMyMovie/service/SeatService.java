package com.BookMyMovie.service;

import com.BookMyMovie.dto.SeatDTO;
import java.util.List;

public interface SeatService {
    SeatDTO createSeat(SeatDTO seatDTO);
    List<SeatDTO> getAllSeats();
    void deleteSeat(Long seatId);
}