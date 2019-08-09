package com.codegym.repository;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note,Long> {
    Iterable<Note> findAllByNoteType(NoteType noteType);
}
