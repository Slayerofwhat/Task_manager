package pl.edu.pja.task_manager.mappers;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.models.*;
import pl.edu.pja.task_manager.models.dto.TaskDTO;
import pl.edu.pja.task_manager.repositories.PriorityRepository;
import pl.edu.pja.task_manager.repositories.StatusRepository;
import pl.edu.pja.task_manager.repositories.TagRepository;
import pl.edu.pja.task_manager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskMapper {
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public TaskMapper(PriorityRepository priorityRepository, StatusRepository statusRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public TaskDTO map(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());

        taskDTO.setTitle(task.getTitle());

        taskDTO.setDescription(task.getDescription());

        taskDTO.setDueDate(task.getDueDate());

        taskDTO.setPriorityId(task.getPriority().getId());
        taskDTO.setPriorityName(task.getPriority().getName());

        taskDTO.setStatusId(task.getStatus().getId());
        taskDTO.setStatusName(task.getStatus().getName());

        taskDTO.setUserId(task.getUser().getId());

        taskDTO.setTagsIdList(task.getTags().stream().map(Tag::getId).toList());
        taskDTO.setTags(task.getTags().stream().map(Tag::getName).toList());
        return taskDTO;
    }

    public boolean map(TaskDTO taskDTO, Task task) {
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        Optional<Priority> priority = priorityRepository.findById(taskDTO.getPriorityId());
        if (priority.isPresent()) {
            task.setPriority(priority.get());
        }
        else {
            return false;
        }
        Optional<Status> status = statusRepository.findById(taskDTO.getStatusId());
        if (status.isPresent()) {
            task.setStatus(status.get());
        }
        else {
            return false;
        }
        Optional<User> user = userRepository.findById(taskDTO.getUserId());
        if (user.isPresent()) {
            task.setUser(user.get());
        }
        else {
            return false;
        }
        List<Tag> tags = new ArrayList<>();
        for (Long tagId : taskDTO.getTagsIdList()) {
            Optional<Tag> tag = tagRepository.findById(tagId);
            if (tag.isPresent()) {
                tags.add(tag.get());
            }
            else {
                return false;
            }
        }
        task.setTags(tags);
        return true;
    }
}
