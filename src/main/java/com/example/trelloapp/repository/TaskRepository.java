package com.example.trelloapp.repository;

import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTable(Table table);

    Optional<Task> findByIdAndTable(Long id, Table table);

    @Query("select max(position) from Task where table.id = :#{#table.id}")
    Integer lastPositionByTableId(Table table);
}