package com.BookMyMovie.repository;

import com.BookMyMovie.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Show, Long>, JpaSpecificationExecutor<Show> {
}
