package pl.edu.pja.task_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.task_manager.models.Status;

public interface StatusRepository extends CrudRepository<Status, Long> {
}
