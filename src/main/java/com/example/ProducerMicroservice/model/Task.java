package com.example.ProducerMicroservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Table
@Entity
@NoArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
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
