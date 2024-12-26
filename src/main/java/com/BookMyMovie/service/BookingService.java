package com.BookMyMovie.service;

import com.BookMyMovie.dto.BookingDTO;
import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    List<BookingDTO> getAllBookings();
    void deleteBooking(Long bookingId);
}