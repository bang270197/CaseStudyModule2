package com.codegym.service;

import com.codegym.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteTypeService {
    Iterable<NoteType> findAll();

    NoteType findById(Long id);

    void save(NoteType noteType);

    void remove(Long id);
}
