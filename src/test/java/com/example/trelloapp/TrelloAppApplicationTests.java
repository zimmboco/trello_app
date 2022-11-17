package com.example.trelloapp;


import com.example.trelloapp.repository.TableRepository;
import com.example.trelloapp.repository.TaskRepository;
import com.example.trelloapp.repository.UserRepository;
import com.example.trelloapp.service.AuthenticationService;
import com.example.trelloapp.service.RoleService;
import com.example.trelloapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TrelloAppApplicationTests {
    @Autowired
    private  TableRepository tableRepository;
    @Autowired
    private  TaskRepository taskRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


//    @BeforeAll
//    public void beforeAll() {
//        Role userRole = new Role();
//        userRole.setRoleName(Role.RoleName.ROLE_USER);
//        roleService.add(userRole);
//        Role adminRole = new Role();
//        adminRole.setRoleName(Role.RoleName.ROLE_ADMIN);
//        roleService.add(adminRole);
//        User user = new User();
//        user.setEmail(new Random().nextInt(10000000) + "zimmboco@gmail.com");
//        user.setPassword("123456");
//        user.setRoles(Set.of(userRole));
//        userService.save(user);
//        //authenticationService.register(new Random().nextInt(10000000) + "zimmboco@gmail.com", "123445");
//
//        Table table = new Table();
//        table.setName("Первое задание!");
//        table.setLocalDateTime(LocalDateTime.now());
//        table.setDescription("Начать делать");
//        table.setPosition(1);
//        table.setUser(user);
//        tableRepository.save(table);
////
//        Task task = new Task();
//        task.setDescription("сделать регистрацию с помощью spring security");
//        task.setPosition(1);
//        task.setTable(table);
//        taskRepository.save(task);


 //   }

    @Test
    public void test() {
        System.out.println("");
    }
}
