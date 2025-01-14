package com.BookMyMovie.repository;

import com.BookMyMovie.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Long>, JpaSpecificationExecutor<Cinema> {
}