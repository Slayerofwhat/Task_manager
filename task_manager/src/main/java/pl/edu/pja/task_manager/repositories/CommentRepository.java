package pl.edu.pja.task_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.task_manager.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Optional<List<Comment>> findByTask_Id(Long id);
}
