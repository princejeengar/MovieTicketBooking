package com.BookMyMovie.repository;

import com.BookMyMovie.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long>, JpaSpecificationExecutor<Seat> {
}