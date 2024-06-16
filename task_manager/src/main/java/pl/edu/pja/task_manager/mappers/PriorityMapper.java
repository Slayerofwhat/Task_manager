package pl.edu.pja.task_manager.mappers;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.models.Priority;
import pl.edu.pja.task_manager.models.dto.PriorityDTO;

@Service
public class PriorityMapper {
    public PriorityDTO map(Priority priority) {
        PriorityDTO dto = new PriorityDTO();
        dto.setName(priority.getName());
        dto.setId(priority.getId());
        return dto;
    }
}
