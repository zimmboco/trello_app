package com.example.trelloapp.service.impl;

import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.User;
import com.example.trelloapp.repository.TableRepository;
import com.example.trelloapp.service.TableService;
import com.example.trelloapp.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    
    private final UserService userService;

    public TableServiceImpl(TableRepository tableRepository, UserService userService) {
        this.tableRepository = tableRepository;
        this.userService = userService;
    }

    @Override
    public Table save(Table table, String email) {
        for (int i = 0; i < table.getTasks().size(); i++) {
            table.getTasks().get(i).setPosition(i + 1);
            table.getTasks().get(i).setTable(table);
        }
        User user = userService.getUser(email);
        Integer lastPosition = tableRepository.lastPositionTableByUserId(user.getId());
        if (lastPosition == null) {
            lastPosition = 0;
        }
        table.setPosition(lastPosition + 1);
        table.setUser(user);
        return tableRepository.save(table);
    }

    @Override
    public Table findById(Long id, String email) {
        User user1 = userService.getUser(email);
        return getTable(id, user1);
    }

    @Override
    public void deleteById(Long id, String email) {
        User user = userService.getUser(email);
        Table table = getTable(id, user);
        tableRepository.delete(table);
    }

    @Override
    public Table updateById(Table table, Long id, String email) {
        User user = userService.getUser(email);
        Table table1 = getTable(id, user);
        table1.setDescription(table.getDescription());
        table1.setName(table.getName());
        table1.setLocalDateTime(table.getLocalDateTime());
        table1.setTasks(table.getTasks());
        tableRepository.save(table1);
        return table;
    }

    @Override
    public List<Table> swapDeskById(Long tableId, Long newPosition, String email) {
        if (newPosition <= 0) {
            newPosition = 0L;
        } else {
            newPosition -= 1L;
        }
        User user = userService.getUser(email);
        Table tableById = getTable(tableId, user);

        List<Table> allTablesByUser = tableRepository.findAllByUserOrderByPosition(user);
        newPosition = newPosition > allTablesByUser.size() - 1 ? allTablesByUser.size() - 1 : newPosition;
        allTablesByUser.remove(tableById);
        allTablesByUser.add(newPosition.intValue(), tableById);
        for (int i = 0; i < allTablesByUser.size(); i++) {
            allTablesByUser.get(i).setPosition(i + 1);
        }
        return tableRepository.saveAll(allTablesByUser);
    }

    @Override
    public List<Table> getAllByEmail(String email) {
        User user = userService.getUser(email);
        return tableRepository.findAllByUserOrderByPosition(user);
    }

    @Override
    public Table getTable(Long id, User user) {
        return tableRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new NoSuchElementException("No such table for user: " + user.getEmail()));
    }
}
