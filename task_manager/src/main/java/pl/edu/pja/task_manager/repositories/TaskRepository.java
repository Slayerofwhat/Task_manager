package pl.edu.pja.task_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.task_manager.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
