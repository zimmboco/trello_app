package com.example.trelloapp.service;

import com.example.trelloapp.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getByName(String roleName);
}
