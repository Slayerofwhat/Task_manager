package pl.edu.pja.task_manager.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.task_manager.mappers.TagMapper;
import pl.edu.pja.task_manager.models.Tag;
import pl.edu.pja.task_manager.models.dto.TagDTO;
import pl.edu.pja.task_manager.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDTO> getTags() {
        List<TagDTO> tagDTOList = new ArrayList<>();
        Iterable<Tag> tags = tagRepository.findAll();

        for (Tag tag : tags) {
            tagDTOList.add(tagMapper.map(tag));
        }

        return tagDTOList;
    }
}
