package com.hillel.dto.transformers;

import com.hillel.dto.WriterNameDto;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

public class WriterNameTransformer implements ResultTransformer {

    @Override
    public WriterNameDto transformTuple(Object[] tuple, String[] aliases) {
        Long id = (Long) tuple[0];
        String fullName = (String) tuple[1];
        return new WriterNameDto(id, fullName);
    }

    @Override
    public List<WriterNameDto> transformList(List collection) {
        return collection;
    }
}