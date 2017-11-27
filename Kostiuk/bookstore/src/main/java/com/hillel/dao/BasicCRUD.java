package com.hillel.dao;

import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public interface BasicCRUD<T> {

    List<T> getAll();

    Optional<T> getById(@NonNull Long id);

    void deleteById(@NonNull Long id);

    Long insert(@NonNull T object);

    Optional<T> update(Long id, Consumer<T> updater);

    void update(T entity, Consumer<T> updater);

    List<T> getByParams(Map<String, Object> params);
}
