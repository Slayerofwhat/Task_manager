package pl.edu.pja.task_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.task_manager.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
