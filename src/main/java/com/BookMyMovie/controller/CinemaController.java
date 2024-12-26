package com.BookMyMovie.controller;

import com.BookMyMovie.dto.CinemaDTO;
import com.BookMyMovie.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/create")
    public ResponseEntity<CinemaDTO> createCinema(@RequestBody CinemaDTO cinemaDTO) {
        CinemaDTO createdCinema = cinemaService.createCinema(cinemaDTO);
        return ResponseEntity.ok(createdCinema);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CinemaDTO>> getAllCinemas() {
        List<CinemaDTO> cinemas = cinemaService.getAllCinema();
        return ResponseEntity.ok(cinemas);
    }

    @DeleteMapping("/delete/cinemaId")
    public ResponseEntity<Void> deleteCinema(@RequestParam Long cinemaId) {
        cinemaService.deleteCinema(cinemaId);
        return ResponseEntity.noContent().build();
    }
}