package com.example.trelloapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Task {
    private Long id;
    private Integer position;
    private String description;
}
