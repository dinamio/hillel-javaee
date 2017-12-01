package com.hillel.service;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface BasicCRUDService<T> {

    Optional<T> insert(@NonNull T object);

    List<T> getAll();

    Optional<T> getById(@NonNull Long id);

    void delete(@NonNull T object);

    Optional<T> update(@NonNull Long id, @NonNull Consumer<T> updater);

}
