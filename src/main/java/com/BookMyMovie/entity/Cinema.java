package com.BookMyMovie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Cinema")
@NoArgsConstructor
public class Cinema{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;
    private String cinemaName;
    private String cinemaAddress;
}
