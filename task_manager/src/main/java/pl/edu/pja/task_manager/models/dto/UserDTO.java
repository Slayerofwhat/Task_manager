package pl.edu.pja.task_manager.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import pl.edu.pja.task_manager.constraints.Digits;
import pl.edu.pja.task_manager.constraints.LowerCase;
import pl.edu.pja.task_manager.constraints.SpecialCharacters;
import pl.edu.pja.task_manager.constraints.UpperCase;

public class UserDTO {
    @JsonIgnore
    private long id;

    @Size(min = 5, max = 20)
    private String username;

    @LowerCase
    @UpperCase
    @Digits
    @SpecialCharacters
    private String password;

    @Email
    private String email;

    private String role;

    @JsonIgnore
    private int completionRate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(int completionRate) {
        this.completionRate = completionRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
