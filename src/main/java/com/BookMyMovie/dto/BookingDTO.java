package com.BookMyMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {
    private Long bookingId;
    private Long seatId;
    private Long appUserId;
    private String customerName;

    // AppUser details
    private String username;
    private String contactNumber;
    private String email;
    private SeatDTO seatDTO;
}