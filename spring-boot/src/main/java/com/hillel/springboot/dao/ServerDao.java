package com.hillel.springboot.dao;

import com.hillel.springboot.model.Server;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by eugen on 11/21/17.
 */
public interface ServerDao extends CrudRepository<Server, Integer> {

    List<Server> findByNameLike(String name);
}
