package pl.edu.pja.task_manager.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.task_manager.models.dto.CommentGetDTO;
import pl.edu.pja.task_manager.models.dto.CommentPostDTO;
import pl.edu.pja.task_manager.models.dto.TaskDTO;
import pl.edu.pja.task_manager.models.dto.UserDTO;
import pl.edu.pja.task_manager.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final CommentService commentService;
    private final PriorityService priorityService;
    private final TagService tagService;

    public TaskController(TaskService taskService, UserService userService, CommentService commentService, PriorityService priorityService, TagService tagService) {
        this.taskService = taskService;
        this.userService = userService;
        this.commentService = commentService;
        this.priorityService = priorityService;
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public String task(@PathVariable long id, Model model) {
        TaskDTO taskDTO = taskService.getTask(id);
        if (taskDTO != null) {
            model.addAttribute("task", taskDTO);
            UserDTO userDTO = userService.getUser(taskDTO.getUserId());
            if (userDTO != null) {
                model.addAttribute("user", userDTO);
            }

            List<CommentGetDTO> commentGetDTOs = commentService.getComments(id);
            if (commentGetDTOs != null){
                model.addAttribute("comments", commentGetDTOs);
            }
            else {
                model.addAttribute("comments", new ArrayList<CommentGetDTO>());
            }

            model.addAttribute("newComment", new CommentPostDTO());
        }
        return "task";
    }

    @GetMapping("/newTask")
    public String newTask(Model model) {
        model.addAttribute("newTask", new TaskDTO());
        model.addAttribute("priorities", priorityService.getPriorities());
        model.addAttribute("tags", tagService.getTags());
        return "newTask";
    }

    @PostMapping("/newTask")
    public String createTask(@Valid TaskDTO newTask, BindingResult bindingResult, Authentication authentication, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newTask", newTask);
            model.addAttribute("priorities", priorityService.getPriorities());
            model.addAttribute("tags", tagService.getTags());
            return "newTask";
        }
        taskService.addTaskFromUser(newTask, authentication.getName());
        return "redirect:/";
    }

    @PostMapping("/{id}/newComment")
    public String createComment(@PathVariable long id, CommentPostDTO commentPostDTO, Authentication authentication){
        commentPostDTO.setUserId(userService.getUserByUsername(authentication.getName()).get().getId());
        commentService.addComment(commentPostDTO, id);
        return "redirect:/tasks/" + id;
    }
}
