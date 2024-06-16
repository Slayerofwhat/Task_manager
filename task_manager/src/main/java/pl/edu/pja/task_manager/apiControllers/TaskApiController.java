package pl.edu.pja.task_manager.apiControllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.task_manager.models.dto.CommentGetDTO;
import pl.edu.pja.task_manager.models.dto.CommentPostDTO;
import pl.edu.pja.task_manager.models.dto.TaskDTO;
import pl.edu.pja.task_manager.services.CommentService;
import pl.edu.pja.task_manager.services.TaskService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/tasks",
                produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskApiController {
    private final TaskService taskService;
    private final CommentService commentService;

    public TaskApiController(TaskService taskService, CommentService commentService) {
        this.taskService = taskService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(){
        List<TaskDTO> tasks = taskService.getTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable long id){
        TaskDTO task = taskService.getTask(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id){
        if(taskService.deleteTask(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO){
        if (taskService.addTask(taskDTO)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@Valid @PathVariable long id, @RequestBody TaskDTO taskDTO){
        if (taskService.updateTask(taskDTO, id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<?> addComment(@PathVariable long taskId, @RequestBody CommentPostDTO commentPostDTO){
        if (commentService.addComment(commentPostDTO, taskId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<List<CommentGetDTO>> getComments(@PathVariable long taskId){
        List<CommentGetDTO> comments = commentService.getComments(taskId);
        if (comments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(comments);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.joining(", "))
                ));
    }
}
