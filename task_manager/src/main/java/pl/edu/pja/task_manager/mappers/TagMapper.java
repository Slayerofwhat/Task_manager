package pl.edu.pja.task_manager.mappers;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.models.Tag;
import pl.edu.pja.task_manager.models.dto.TagDTO;

@Service
public class TagMapper {
    public TagDTO map(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setName(tag.getName());
        dto.setId(tag.getId());
        return dto;
    }
}
