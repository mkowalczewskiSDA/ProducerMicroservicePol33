package com.example.ProducerMicroservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Integer id;
    private String description;
    private boolean completed;
    private Integer completionPercentage;
    public UserDTO user;

    public TaskDTO(String description, boolean completed, Integer completionPercentage, UserDTO user) {
        this.description = description;
        this.completed = completed;
        this.completionPercentage = completionPercentage;
        this.user = user;
    }

    public TaskDTO(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }
}
