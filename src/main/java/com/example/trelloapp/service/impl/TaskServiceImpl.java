package com.example.trelloapp.service.impl;

import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.Task;
import com.example.trelloapp.model.User;
import com.example.trelloapp.repository.TaskRepository;
import com.example.trelloapp.service.TableService;
import com.example.trelloapp.service.TaskService;
import com.example.trelloapp.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TableService tableService;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           TableService tableService,
                           UserService userService) {
        this.taskRepository = taskRepository;
        this.tableService = tableService;
        this.userService = userService;
    }

    @Override
    public Task save(Task task, Long tableId, String email) {
        User user = userService.getUser(email);
        Table table = tableService.getTable(tableId, user);
        Integer lastTaskPosition = taskRepository.lastPositionByTableId(table);
        if (lastTaskPosition == null) {
            lastTaskPosition = 0;
        }
        task.setPosition(lastTaskPosition + 1);
        task.setTable(table);
        return taskRepository.save(task);
    }

    @Override
    public Task findById(Long tableId, Long id, String email) {
        User user = userService.getUser(email);
        Table table = tableService.getTable(tableId, user);
        Task taskById = getTask(id, table);
        return taskById;
    }

    @Override
    public void deleteById(Long tableId, Long id, String email) {
        User user = userService.getUser(email);
        Table table = tableService.getTable(tableId, user);
        Task taskById = getTask(id, table);
        taskRepository.delete(taskById);
    }

    @Override
    public Task updateById(Task task, Long taskId, Long tableId, String email) {
        User user = userService.getUser(email);
        Table table = tableService.getTable(tableId, user);
        Task task1 = getTask(taskId, table);
        task1.setDescription(task.getDescription());
        taskRepository.save(task1);
        return task;
    }

    @Override
    public List<Task> swapTaskInTableById(Long tableId, Long taskId, Long swap, String email) {
        if (swap <= 0) {
            swap = 1L;
        } else {
            swap -= 1L;
        }
        User user = userService.getUser(email);
        Table table = tableService.getTable(tableId, user);
        Task taskById = getTask(taskId, table);
        List<Task> allByTable = taskRepository.findAllByTable(table);
        allByTable.remove(taskById);
        allByTable.add(swap.intValue(), taskById);
        for (int i = 0; i < allByTable.size(); i++) {
            allByTable.get(i).setPosition(i + 1);
        }
        taskRepository.saveAll(allByTable);
        return allByTable;
    }
    @Override
    public List<Task> swapTaskAnotherTableById(Long tableId, Long taskId, Long newTaskPosition,
                                               Long targetTableId, String email) {
        if (newTaskPosition <= 0) {
            newTaskPosition = 0L;
        } else {
            newTaskPosition -= 1L;
        }
        User user = userService.getUser(email);
        Table table = tableService.getTable(tableId, user);
        Task taskById = getTask(taskId, table);
        taskById.setPosition(newTaskPosition.intValue());
        table.getTasks().remove(taskById);
        if (Objects.equals(tableId, targetTableId)) {
            newTaskPosition = newTaskPosition > table.getTasks().size() - 1 ? table.getTasks().size() - 1 : newTaskPosition;
            table.getTasks().add(newTaskPosition.intValue(), taskById);
            setPositions(table);
            return taskRepository.saveAll(table.getTasks());
        } else {
            Table targetTable = tableService.getTable(targetTableId, user);
            taskById.setTable(targetTable);
            newTaskPosition = newTaskPosition > targetTable.getTasks().size() - 1 ? targetTable.getTasks().size() - 1 : newTaskPosition;
            targetTable.getTasks().add(newTaskPosition.intValue(), taskById);
            setPositions(targetTable);
            return taskRepository.saveAll(targetTable.getTasks());
        }
    }

    private void setPositions(Table table) {
        List<Task> tasks = table.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setPosition(i + 1);
        }
    }

    private Task getTask(Long taskId, Table table) {
        return taskRepository.findByIdAndTable(taskId, table).orElseThrow(
                () -> new NoSuchElementException("Can`t find task by id: " + taskId));
    }
}
