package com.BookMyMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserDTO {
    private Long userId;
    private String username;
    private String contactNumber;
    private String email;
}
