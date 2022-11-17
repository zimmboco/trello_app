package com.example.trelloapp.service.impl;

import com.example.trelloapp.model.Role;
import com.example.trelloapp.repository.RoleRepository;
import com.example.trelloapp.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roleRepository.findByRoleName(Role.RoleName.ROLE_USER);
    }
}
