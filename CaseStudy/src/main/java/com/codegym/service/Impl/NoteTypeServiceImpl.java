package com.codegym.service.Impl;

import com.codegym.model.NoteType;
import com.codegym.repository.NoteRepository;
import com.codegym.repository.NoteTypeRepository;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class NoteTypeServiceImpl implements NoteTypeService {
    @Autowired
    private NoteTypeRepository noteTypeRepository;


    @Override
    public Iterable<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }

    @Override
    public NoteType findById(Long id) {
        return noteTypeRepository.findOne(id);
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Long id) {
        noteTypeRepository.delete(id);
    }
}
