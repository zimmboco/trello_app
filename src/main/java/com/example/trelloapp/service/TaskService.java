package com.example.trelloapp.service;

import com.example.trelloapp.model.Task;
import java.util.List;

public interface TaskService {
    Task save(Task task, Long tableId, String email);
    Task findById(Long tableId, Long id, String email);
    void deleteById(Long tableId, Long id, String email);

    Task updateById(Task task, Long taskId, Long tableId, String email);

    List<Task> swapTaskInTableById(Long tableId, Long taskId, Long swap, String email);

    List<Task> swapTaskAnotherTableById(Long tableId, Long taskId, Long swap, Long swapTableId,
                                        String email);
}
