package com.BookMyMovie.controller;

import com.BookMyMovie.dto.AppUserDTO;
import com.BookMyMovie.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/create")
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUserDTO appUserDTO) {
        AppUserDTO createdUser = appUserService.createUser(appUserDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        List<AppUserDTO> users = appUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam Long userId) {
        appUserService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}