package com.example.trelloapp.dto;

import com.example.trelloapp.lib.ValidEmail;
import com.example.trelloapp.model.Role;
import com.example.trelloapp.model.Table;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
    @NotBlank
    @Size(min = 6)
    private String password;
    @ValidEmail
    private String email;
    private List<Table> tables;
    private Set<Role> roles;
}
