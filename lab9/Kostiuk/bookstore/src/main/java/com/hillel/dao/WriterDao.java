package com.hillel.dao;

import com.hillel.dto.WriterNameDto;
import com.hillel.model.Writer;

import java.util.List;
import java.util.Optional;

public interface WriterDao extends BasicCRUD<Writer> {


    List<WriterNameDto> getAllWriterNames();

    Optional<Writer> getByFullName(String fullName);
}
