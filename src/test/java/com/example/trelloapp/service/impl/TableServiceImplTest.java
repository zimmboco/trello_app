package com.example.trelloapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.example.trelloapp.model.Role;
import com.example.trelloapp.model.Table;
import com.example.trelloapp.model.User;
import com.example.trelloapp.repository.TableRepository;
import com.example.trelloapp.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TableServiceImplTest {
    User user;
    List<Table> tables1;
    Table testTable;

    @BeforeEach
    void setUp() {
        user = getUser();
        tables1 = getTables();
        testTable = tables1.get(0);

        Mockito.when(tableRepository.findByIdAndUser(any(), any())).thenReturn(Optional.of(testTable));
        Mockito.when(tableRepository.findAllByUserOrderByPosition(any())).thenReturn(tables1);
        Mockito.when(tableRepository.saveAll(any())).thenReturn(tables1);
        Mockito.when(userService.getUser(any())).thenReturn(user);
    }

    @InjectMocks
    private TableServiceImpl tableService;

    @Mock
    private TableRepository tableRepository;
    @Mock
    private UserService userService;

    @Test
    void swapDeskById() {
        List<Table> tables =
                tableService.swapDeskById(testTable.getId(), 3L, getUser().getEmail());
        String name = tables.get(2).getName();
        Integer position = testTable.getPosition();
        assertEquals(testTable.getId(), 1);
        assertEquals(position, 3);
        assertEquals("table1", name);

        tables =
                tableService.swapDeskById(testTable.getId(), -1L, getUser().getEmail());

        assertEquals(1, testTable.getPosition());
        assertEquals(1, tables.get(0).getId());
        assertEquals("table1", tables.get(0).getName());

        tables =
                tableService.swapDeskById(testTable.getId(), 100L, getUser().getEmail());

        assertEquals(3, testTable.getPosition());
        assertEquals(1, tables.get(2).getId());
        assertEquals("table1", tables.get(2).getName());
    }
    private User getUser() {
        User user = new User();
        String email = "zimmboco@gmail.com";
        user.setEmail(email);
        user.setPassword("123455");
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(userRole));
        return user;
    }
    private Table getTable() {
        Table table1 = new Table();
        table1.setLocalDateTime(LocalDateTime.now());
        table1.setName("table");
        table1.setUser(user);
        table1.setDescription("create");
        table1.setTasks(new ArrayList<>());
        table1.setPosition(1);
        table1.setId(1L);
        return table1;
    }

    private List<Table> getTables() {
        List<Table> tables = new ArrayList<>(List.of(getTable(), getTable(), getTable()));
        for (int i = 0; i < tables.size(); i++) {
            Table table1 = tables.get(i);
            table1.setId(i + 1L);
            table1.setPosition(i + 1);
            table1.setName(table1.getName() + (i + 1));
            table1.setDescription(table1.getDescription() + i);
        }
        return tables;
    }
}