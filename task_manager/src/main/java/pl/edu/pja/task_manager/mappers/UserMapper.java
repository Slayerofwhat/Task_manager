package pl.edu.pja.task_manager.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.models.User;
import pl.edu.pja.task_manager.models.dto.UserDTO;
import pl.edu.pja.task_manager.models.dto.UserRegistrationDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setCompletionRate(user.getCompletionRate());
        return userDTO;
    }

    public boolean map(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setCompletionRate(userDTO.getCompletionRate());
        return true;
    }

    public boolean map(UserRegistrationDTO userRegistrationDTO, User user) {
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole("USER");
        user.setCompletionRate(0);
        return true;
    }
}
