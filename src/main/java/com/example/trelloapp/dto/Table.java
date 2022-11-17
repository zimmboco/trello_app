package com.example.trelloapp.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Table {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime localDateTime;
    private List<Task> tasks = new ArrayList<>();
}
