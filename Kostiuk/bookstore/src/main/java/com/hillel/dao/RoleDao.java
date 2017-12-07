package com.hillel.dao;

import com.hillel.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleDao extends CrudRepository<Role, Long> {

    Optional<Role> getByAuthority(String authority);
}
