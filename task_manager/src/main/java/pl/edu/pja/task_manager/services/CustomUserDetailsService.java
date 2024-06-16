package pl.edu.pja.task_manager.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserByUsername(username)
                .map(dto -> User.builder()
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .roles(dto.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
