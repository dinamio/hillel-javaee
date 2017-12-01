package com.hillel.dao;

import com.hillel.model.Writer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WriterDao extends CrudRepository<Writer, Long> {


    Optional<Writer> getByFullName(String fullName);


}
