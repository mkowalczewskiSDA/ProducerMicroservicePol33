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

}
