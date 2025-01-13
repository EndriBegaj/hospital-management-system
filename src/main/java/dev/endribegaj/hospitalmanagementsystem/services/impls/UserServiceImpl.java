package dev.endribegaj.hospitalmanagementsystem.services.impls;

import dev.endribegaj.hospitalmanagementsystem.dtos.UserDto;
import dev.endribegaj.hospitalmanagementsystem.dtos.UserRegistrationRequestDto;
import dev.endribegaj.hospitalmanagementsystem.exception.EmailExistException;
import dev.endribegaj.hospitalmanagementsystem.exception.UserNotFoundException;
import dev.endribegaj.hospitalmanagementsystem.exception.UserNameExistException;
import dev.endribegaj.hospitalmanagementsystem.exception.WrongPasswordException;
import dev.endribegaj.hospitalmanagementsystem.mappers.UserMapper;
import dev.endribegaj.hospitalmanagementsystem.models.User;
import dev.endribegaj.hospitalmanagementsystem.repsitories.UserRepository;
import dev.endribegaj.hospitalmanagementsystem.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(UserRegistrationRequestDto userRegDto) {
        User user = userMapper.fromUserRegistrationDto(userRegDto);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNameExistException();
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistException();
        }

        //TODO: implement the logic to encrypt/encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //kur e ruan na kthen User-in e ruajtur se bashku me ID-ne e gjeneruar
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);

    }

    @Override
    public UserDto login(String username, String password) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new WrongPasswordException();
        }
        return userMapper.toDto(user.get());
    }
}










