package pl.edu.pja.task_manager.services;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.mappers.UserMapper;
import pl.edu.pja.task_manager.models.User;
import pl.edu.pja.task_manager.models.dto.UserDTO;
import pl.edu.pja.task_manager.models.dto.UserRegistrationDTO;
import pl.edu.pja.task_manager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.map(user));
        }
        return userDTOs;
    }

    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::map).orElse(null);
    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    public boolean addUser(UserDTO userDTO) {
        User user = new User();
        if (userMapper.map(userDTO, user)){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(UserDTO userDTO, Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return false;
        }

        User newUser = new User();
        if (!userMapper.map(userDTO, newUser)){
            return false;
        }

        newUser.setId(id);
        userRepository.save(newUser);
        return true;
    }

    public void register(UserRegistrationDTO userRegistrationDTO){
        User user = new User();
        userMapper.map(userRegistrationDTO, user);
        userRepository.save(user);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::map);
    }
}
