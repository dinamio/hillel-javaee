package com.hillel.service;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public interface BasicCRUDService<T> {

    Optional<T> insert(@NonNull T object);

    List<T> getAll();

    Optional<T> getById(@NonNull Long id);

    void delete(@NonNull T object);

    void update(@NonNull Long id, @NonNull Consumer<T> updater);

    void update(T entity, Consumer<T> updater);

    default List<T> getByParams(Map<String, Object> params) {
        return ImmutableList.of();
    }

}
