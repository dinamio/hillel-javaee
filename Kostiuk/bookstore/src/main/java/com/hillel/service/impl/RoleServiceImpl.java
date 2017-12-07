package com.hillel.service.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.RoleDao;
import com.hillel.model.Role;
import com.hillel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao RoleDao;


    @Override
    public Optional<Role> insert(Role Role) {
        return Optional.ofNullable(RoleDao.save(Role));
    }

    @Override
    public List<Role> getAll() {
        return ImmutableList.copyOf(RoleDao.findAll());
    }

    @Override
    public Optional<Role> getById(Long id) {
        return Optional.ofNullable(RoleDao.findOne(id));
    }

    @Override
    public void delete(Role Role) {
        Optional.ofNullable(Role).ifPresent(RoleDao::delete);
    }

    @Override
    public Optional<Role> update(Long id, Consumer<Role> updater) {
        Role Role = RoleDao.findOne(id);
        updater.accept(Role);
        return Optional.ofNullable(Role);
    }

    @Override
    public Optional<Role> getByAuthority(String authority) {
        return RoleDao.getByAuthority(authority);
    }
}
