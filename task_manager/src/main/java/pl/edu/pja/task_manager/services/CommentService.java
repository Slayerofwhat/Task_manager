package pl.edu.pja.task_manager.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.mappers.CommentMapper;
import pl.edu.pja.task_manager.models.Comment;
import pl.edu.pja.task_manager.models.dto.CommentGetDTO;
import pl.edu.pja.task_manager.models.dto.CommentPostDTO;
import pl.edu.pja.task_manager.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public boolean addComment(CommentPostDTO commentPostDTO, Long taskId) {
        Comment comment = new Comment();
        if (commentMapper.map(commentPostDTO, comment, taskId)){
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    public List<CommentGetDTO> getComments(long taskId){
        Optional<List<Comment>> comments = commentRepository.findByTask_Id(taskId);
        if (comments.isPresent()){
            List<CommentGetDTO> commentGetDTOS = new ArrayList<>();
            for (Comment comment : comments.get()){
                commentGetDTOS.add(commentMapper.mapToGet(comment));
            }
            return commentGetDTOS;
        }
        else return null;
    }
}
