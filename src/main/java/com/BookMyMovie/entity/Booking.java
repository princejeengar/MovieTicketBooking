package com.BookMyMovie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "BookingDetails")
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seatId", nullable = false)
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUserId", nullable = false)
    private AppUser appUser;

    private String customerName;
}