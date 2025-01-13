package dev.endribegaj.hospitalmanagementsystem.services;

import dev.endribegaj.hospitalmanagementsystem.dtos.UserDto;
import dev.endribegaj.hospitalmanagementsystem.dtos.UserRegistrationRequestDto;

public interface UserService {
    UserDto register(UserRegistrationRequestDto userRegDto);

    UserDto login(String username, String password);

}
