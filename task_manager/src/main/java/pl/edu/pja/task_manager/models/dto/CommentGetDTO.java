package pl.edu.pja.task_manager.models.dto;

import java.sql.Time;

public class CommentGetDTO {
    private String content;

    private Time time;

    private String username;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
