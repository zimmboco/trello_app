package com.example.trelloapp.controller;

import com.example.trelloapp.dto.Table;
import com.example.trelloapp.mapper.Mapper;
import com.example.trelloapp.service.TableService;
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
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;
    private final Mapper mapper;

    public TableController(TableService tableService, Mapper mapper) {
        this.tableService = tableService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Table> create(@RequestBody Table table,
                                        Authentication auth) {
        com.example.trelloapp.model.Table saveResult = tableService.save(mapper.toModel(table), auth.getName());
        Table body = mapper.toDto(saveResult);
        return ResponseEntity.ok().body(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Table> get(@PathVariable Long id,
                                     Authentication auth) {
        return ResponseEntity.ok().body(mapper.toDto(tableService.findById(id, auth.getName())));
    }

    @GetMapping
    public ResponseEntity<List<Table>> getAll(Authentication auth) {
        List<com.example.trelloapp.model.Table> all = tableService.getAllByEmail(auth.getName());
       return ResponseEntity.ok().body(mapper.toDtoList(all));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication auth) {
        tableService.deleteById(id, auth.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Table> update(@RequestBody Table table,
                                        @PathVariable Long id,
                                        Authentication auth) {
        com.example.trelloapp.model.Table updateResult =
                tableService.updateById(mapper.toModel(table), id, auth.getName());
        return ResponseEntity.ok().body(mapper.toDto(updateResult));
    }

    @PutMapping("/{id}/swap")
    public ResponseEntity<List<Table>> swapDeskById(@PathVariable Long id,
                                    @RequestParam Long newPosition,
                                    Authentication auth) {
        List<com.example.trelloapp.model.Table> tablesModel =
                tableService.swapDeskById(id, newPosition, auth.getName());
        return ResponseEntity.ok().body(mapper.toDtoList(tablesModel));
    }
}


