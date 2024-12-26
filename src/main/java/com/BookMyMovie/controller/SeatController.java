package com.BookMyMovie.controller;

import com.BookMyMovie.dto.SeatDTO;
import com.BookMyMovie.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/create")
    public ResponseEntity<SeatDTO> createSeat(@RequestBody SeatDTO seatDTO) {
        SeatDTO createdSeat = seatService.createSeat(seatDTO);
        return ResponseEntity.ok(createdSeat);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        List<SeatDTO> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteSeat(@RequestParam Long seatId) {
        seatService.deleteSeat(seatId);
        return ResponseEntity.noContent().build();
    }
}