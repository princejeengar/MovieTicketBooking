package com.BookMyMovie.controller;

import com.BookMyMovie.dto.ShowDTO;
import com.BookMyMovie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/create")
    public ResponseEntity<ShowDTO> createShow(@RequestBody ShowDTO showDTO) {
        ShowDTO createdShow = showService.createShow(showDTO);
        return ResponseEntity.ok(createdShow);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ShowDTO>> getAllShows() {
        List<ShowDTO> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteShow(@RequestParam Long showId) {
        showService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }
}