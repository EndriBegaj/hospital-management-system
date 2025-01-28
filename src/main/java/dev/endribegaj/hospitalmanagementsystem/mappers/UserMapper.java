package dev.endribegaj.hospitalmanagementsystem.mappers;

import dev.endribegaj.hospitalmanagementsystem.dtos.UserDto;
import dev.endribegaj.hospitalmanagementsystem.dtos.UserRegistrationRequestDto;
import dev.endribegaj.hospitalmanagementsystem.infrastructure.mapping.SimpleMapper;
import dev.endribegaj.hospitalmanagementsystem.models.User;

import org.mapstruct.Mapper;


@Mapper (componentModel = "spring")
public interface UserMapper extends SimpleMapper<User, UserDto> {
    User fromUserRegistrationDto(UserRegistrationRequestDto userRegDto);
}
