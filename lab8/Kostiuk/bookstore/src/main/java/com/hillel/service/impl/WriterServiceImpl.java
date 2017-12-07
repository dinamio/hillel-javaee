package com.hillel.service.impl;

import com.hillel.dao.WriterDao;
import com.hillel.dao.impl.hibernate.WriterDaoImpl;
import com.hillel.dto.WriterNameDto;
import com.hillel.model.Writer;
import com.hillel.service.WriterService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class WriterServiceImpl implements WriterService {


    @Getter
    static volatile WriterService instance;

    public static WriterService getInstance() {
        if (instance == null) {
            synchronized (WriterServiceImpl.class) {
                if (instance == null) {
                    instance = new WriterServiceImpl();
                }
            }
        }
        return instance;
    }


    private WriterDao writerDao;

    private WriterServiceImpl() {
        writerDao = WriterDaoImpl.getInstance();
    }

    @Override
    public Optional<Writer> insert(Writer writer) {
        Optional.ofNullable(writerDao.insert(writer)).ifPresent(writer::setId);
        return Optional.of(writer);
    }

    @Override
    public List<Writer> getAll() {
        return writerDao.getAll();
    }

    @Override
    public Optional<Writer> getById(Long id) {
        return writerDao.getById(id);
    }

    @Override
    public void delete(Writer writer) {
        Optional.ofNullable(writer).map(Writer::getId).ifPresent(writerDao::deleteById);
    }

    @Override
    public void update(Long id, Consumer<Writer> updater) {
        writerDao.update(id, updater);
    }

    @Override
    public void update(Writer entity, Consumer<Writer> updater) {
        writerDao.update(entity, updater);
    }

    @Override
    public List<WriterNameDto> getAllWriterNames() {
        return writerDao.getAllWriterNames();
    }

    @Override
    public Optional<Writer> getByFullName(String fullName) {
        return writerDao.getByFullName(fullName);
    }
}
