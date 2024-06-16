package pl.edu.pja.task_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.task_manager.models.Priority;

public interface PriorityRepository extends CrudRepository<Priority, Long> {
}
