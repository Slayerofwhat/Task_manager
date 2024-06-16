package pl.edu.pja.task_manager.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.mappers.TaskMapper;
import pl.edu.pja.task_manager.models.Task;
import pl.edu.pja.task_manager.models.User;
import pl.edu.pja.task_manager.models.dto.TaskDTO;
import pl.edu.pja.task_manager.repositories.TaskRepository;
import pl.edu.pja.task_manager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> getTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOs.add(taskMapper.map(task));
        }
        return taskDTOs;
    }

    public TaskDTO getTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(taskMapper::map).orElse(null);
    }

    public boolean deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            return true;
        }
        return false;
    }

    public boolean addTask(TaskDTO taskDTO) {
        Task task = new Task();
        if (taskMapper.map(taskDTO, task)){
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    public boolean addTaskFromUser(TaskDTO taskDTO, String username) {
        Task task = new Task();
        taskDTO.setUserId(userRepository.findByUsername(username).map(User::getId).orElse(null));
        taskDTO.setStatusId(1L);

        if (taskMapper.map(taskDTO, task)){
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    public boolean updateTask(TaskDTO taskDTO, Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()){
            return false;
        }

        Task newTask = new Task();
        if (!taskMapper.map(taskDTO, newTask)){
            return false;
        }

        newTask.setId(id);
        taskRepository.save(newTask);
        return true;
    }
}
