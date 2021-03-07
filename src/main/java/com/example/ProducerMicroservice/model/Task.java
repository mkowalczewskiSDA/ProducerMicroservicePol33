package com.example.ProducerMicroservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Table
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    @NotNull
    private String description;
    @Column
    private boolean completed;
    @Column
    @Min(0)
    @Max(100)
    private Integer completionPercentage;
    @ManyToOne(fetch = FetchType.LAZY)
    public User user;
}
