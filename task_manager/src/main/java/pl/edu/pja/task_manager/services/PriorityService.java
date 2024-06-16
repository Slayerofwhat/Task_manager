package pl.edu.pja.task_manager.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.mappers.PriorityMapper;
import pl.edu.pja.task_manager.models.Priority;
import pl.edu.pja.task_manager.models.dto.PriorityDTO;
import pl.edu.pja.task_manager.repositories.PriorityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;
    private final PriorityMapper priorityMapper;

    public PriorityService(PriorityRepository priorityRepository, PriorityMapper priorityMapper) {
        this.priorityRepository = priorityRepository;
        this.priorityMapper = priorityMapper;
    }

    public List<PriorityDTO> getPriorities(){
        List<PriorityDTO> prioritiesDTO = new ArrayList<>();
        Iterable<Priority> prioritiesList = priorityRepository.findAll();

        for (Priority priority : prioritiesList) {
            prioritiesDTO.add(priorityMapper.map(priority));
        }

        return prioritiesDTO;
    }
}
