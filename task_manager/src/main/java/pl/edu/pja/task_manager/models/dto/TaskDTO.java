package pl.edu.pja.task_manager.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pja.task_manager.constraints.FutureDate;

import java.util.Date;
import java.util.List;

public class TaskDTO {
    @JsonIgnore
    private long id;

    @Size(min = 1, max = 30)
    private String title;

    @Size(min = 1, max = 100)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureDate
    private Date dueDate;

    private Long priorityId;

    @JsonIgnore
    private String priorityName;

    private Long statusId;

    @JsonIgnore
    private String statusName;

    private List<Long> tagsIdList;

    @JsonIgnore
    private List<String> tags;

    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public List<Long> getTagsIdList() {
        return tagsIdList;
    }

    public void setTagsIdList(List<Long> tagsIdList) {
        this.tagsIdList = tagsIdList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
