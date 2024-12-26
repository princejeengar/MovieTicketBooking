package com.BookMyMovie.service.serviceImpl;

import com.BookMyMovie.dto.AppUserDTO;
import com.BookMyMovie.entity.AppUser;
import com.BookMyMovie.repository.AppUserRepo;
import com.BookMyMovie.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppUserDTO createUser(AppUserDTO appUserDTO) {
        AppUser appUser = modelMapper.map(appUserDTO, AppUser.class);
        AppUser savedUser = appUserRepo.save(appUser);
        return modelMapper.map(savedUser, AppUserDTO.class);
    }

    @Override
    public List<AppUserDTO> getAllUsers() {
        List<AppUser> users = appUserRepo.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, AppUserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        appUserRepo.deleteById(userId);
    }
}
