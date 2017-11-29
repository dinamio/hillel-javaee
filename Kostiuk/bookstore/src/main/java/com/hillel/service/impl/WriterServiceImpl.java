package com.hillel.service.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.WriterDao;
import com.hillel.dto.WriterNameDto;
import com.hillel.model.Writer;
import com.hillel.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterDao writerDao;


    @Override
    public Optional<Writer> insert(Writer writer) {
        return Optional.ofNullable(writerDao.save(writer));
    }

    @Override
    public List<Writer> getAll() {
        return ImmutableList.copyOf(writerDao.findAll());
    }

    @Override
    public Optional<Writer> getById(Long id) {
        return Optional.ofNullable(writerDao.findOne(id));
    }

    @Override
    public void delete(Writer writer) {
        Optional.ofNullable(writer).ifPresent(writerDao::delete);
    }

    @Override
    public Optional<Writer> update(Long id, Consumer<Writer> updater) {
        Writer writer = writerDao.findOne(id);
        updater.accept(writer);
        return Optional.ofNullable(writer);
    }

    @Override
    public List<WriterNameDto> getAllWriterNames() {
        return getAll().stream().map(w-> new WriterNameDto(w.getId(), w.getFullName())).collect(Collectors.toList());
    }

    @Override
    public Optional<Writer> getByFullName(String fullName) {
        return writerDao.getByFullName(fullName);
    }
}
