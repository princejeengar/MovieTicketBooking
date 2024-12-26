package com.BookMyMovie.service;

import com.BookMyMovie.dto.AppUserDTO;
import java.util.List;

public interface AppUserService {
    AppUserDTO createUser(AppUserDTO appUserDTO);
    List<AppUserDTO> getAllUsers();
    void deleteUser(Long userId);
}