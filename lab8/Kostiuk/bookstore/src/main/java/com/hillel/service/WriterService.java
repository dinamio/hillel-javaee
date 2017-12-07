package com.hillel.service;

import com.hillel.dto.WriterNameDto;
import com.hillel.model.Writer;

import java.util.List;
import java.util.Optional;

public interface WriterService extends BasicCRUDService<Writer> {


    List<WriterNameDto> getAllWriterNames();


    Optional<Writer> getByFullName(String fullName);
}
