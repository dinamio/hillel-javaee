package com.hillel.service;

import com.hillel.model.Role;

import java.util.Optional;

public interface RoleService extends BasicCRUDService<Role> {

    Optional<Role> getByAuthority(String authority);
}
