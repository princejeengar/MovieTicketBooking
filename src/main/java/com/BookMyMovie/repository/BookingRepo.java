package com.BookMyMovie.repository;

import com.BookMyMovie.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
}