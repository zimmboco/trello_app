package com.example.trelloapp.service;

import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.User;
import java.util.List;

public interface TableService {
    Table save(Table table, String email);

    Table findById(Long id, String email);

    void deleteById(Long id, String email);

    Table updateById(Table table, Long id, String email);

    List<Table> swapDeskById(Long tableId, Long newPosition, String email);

    List<Table> getAllByEmail(String email);

    Table getTable(Long id, User user);
}
