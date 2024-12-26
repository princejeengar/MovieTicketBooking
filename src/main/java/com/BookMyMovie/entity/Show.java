package com.BookMyMovie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ShowDetails")
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;
    private String showTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinemaId", nullable = false)
    private Cinema cinema;
}