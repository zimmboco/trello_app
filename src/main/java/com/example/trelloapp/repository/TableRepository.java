package com.example.trelloapp.repository;

import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
    List<Table> findAllByUserOrderByPosition(User user);

    Optional<Table> findByIdAndUser(Long id, User user);

    @Query("select max(position) from Table where user.id = :id")
    Integer lastPositionTableByUserId(Long id);
}
