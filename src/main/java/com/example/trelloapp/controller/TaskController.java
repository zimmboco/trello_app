package com.example.trelloapp.controller;

import com.example.trelloapp.dto.Task;
import com.example.trelloapp.mapper.Mapper;
import com.example.trelloapp.service.TaskService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables/{tableId}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final Mapper mapper;

    public TaskController(TaskService taskService, Mapper mapper) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task,
                                       @PathVariable Long tableId,
                                       Authentication auth) {
        com.example.trelloapp.model.Task modelTask = mapper.toModel(task);
        com.example.trelloapp.model.Task saveTask =
                taskService.save(modelTask, tableId, auth.getName());
        return ResponseEntity.ok().body(mapper.toDto(saveTask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long tableId,
                                            @PathVariable Long id,
                                            Authentication auth) {
        return ResponseEntity.ok().body(mapper.toDto
                (taskService.findById(tableId, id, auth.getName())));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long tableId,
                           @PathVariable Long id,
                           Authentication auth) {
        taskService.deleteById(tableId, id, auth.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long tableId,
                                       @PathVariable Long id,
                                       @RequestBody Task task,
                                       Authentication auth) {
        com.example.trelloapp.model.Task task1 = mapper.toModel(task);
        return ResponseEntity.ok().body(mapper.toDto
                (taskService.updateById(task1, id, tableId, auth.getName())));
    }

    @PutMapping("/{id}/{swap}")
    public ResponseEntity<List<Task>> swapTaskInTableById(@PathVariable Long tableId,
                                                          @PathVariable Long id,
                                                          @PathVariable Long swap,
                                                          Authentication auth) {
        List<com.example.trelloapp.model.Task> tasks =
                taskService.swapTaskInTableById(tableId, id, swap, auth.getName());
        List<Task> taskListDto = new ArrayList<>();
        for (com.example.trelloapp.model.Task taskModel : tasks) {
            taskListDto.add(mapper.toDto(taskModel));
        }
        return ResponseEntity.ok().body(taskListDto);
    }

    @PutMapping("/{taskId}/swap")
    public ResponseEntity<List<Task>> swapTaskAnotherTableById(@PathVariable Long tableId,
                                                               @PathVariable Long taskId,
                                                               @RequestParam Long newTaskPosition,
                                                               @RequestParam Long targetTable,
                                                               Authentication auth) {
        List<com.example.trelloapp.model.Task> tasks =
                taskService.swapTaskAnotherTableById(tableId, taskId, newTaskPosition, targetTable,
                        auth.getName());
        List<Task> taskListDto = new ArrayList<>();
        for (com.example.trelloapp.model.Task taskModel : tasks) {
            taskListDto.add(mapper.toDto(taskModel));
        }
        return ResponseEntity.ok().body(taskListDto);
    }
}
