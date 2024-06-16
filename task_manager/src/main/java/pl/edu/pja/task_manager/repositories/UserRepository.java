package pl.edu.pja.task_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.task_manager.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
