package pl.edu.pja.task_manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import pl.edu.pja.task_manager.constraints.Digits;
import pl.edu.pja.task_manager.constraints.LowerCase;
import pl.edu.pja.task_manager.constraints.SpecialCharacters;
import pl.edu.pja.task_manager.constraints.UpperCase;

import java.util.List;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private int completionRate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

//    public List<Task> getTaskAssigned() {
//        return taskAssigned;
//    }
//
//    public void setTaskAssigned(List<Task> taskAssigned) {
//        this.taskAssigned = taskAssigned;
//    }
//
//    public List<Comment> getCommentsPosted() {
//        return commentsPosted;
//    }
//
//    public void setCommentsPosted(List<Comment> commentsPosted) {
//        this.commentsPosted = commentsPosted;
//    }
}
