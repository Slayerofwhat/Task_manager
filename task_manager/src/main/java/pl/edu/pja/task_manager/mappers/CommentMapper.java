package pl.edu.pja.task_manager.mappers;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.models.Comment;
import pl.edu.pja.task_manager.models.Task;
import pl.edu.pja.task_manager.models.User;
import pl.edu.pja.task_manager.models.dto.CommentGetDTO;
import pl.edu.pja.task_manager.models.dto.CommentPostDTO;
import pl.edu.pja.task_manager.repositories.TaskRepository;
import pl.edu.pja.task_manager.repositories.UserRepository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class CommentMapper {
    UserRepository userRepository;
    TaskRepository taskRepository;

    public CommentMapper(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public boolean map(CommentPostDTO commentPostDTO, Comment comment, Long taskId){
        comment.setContent(commentPostDTO.getContent());

        Optional<User> user = userRepository.findById(commentPostDTO.getUserId());
        if(user.isPresent()){
            comment.setUser(user.get());
        }
        else return false;

        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            comment.setTask(task.get());
        }
        else return false;

        comment.setTime(Time.valueOf(LocalTime.now()));

        return true;
    }

    public CommentGetDTO mapToGet(Comment comment){
        CommentGetDTO commentGetDTO = new CommentGetDTO();
        commentGetDTO.setContent(comment.getContent());
        commentGetDTO.setTime(comment.getTime());
        commentGetDTO.setUsername(comment.getUser().getUsername());
        return commentGetDTO;
    }
}
