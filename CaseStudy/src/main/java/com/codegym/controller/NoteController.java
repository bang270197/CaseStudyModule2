package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import com.codegym.service.NoteService;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteTypeService noteTypeService;

//    @ModelAttribute("NoteTypes")
//    public Iterable<NoteType> noteTypes(){
//        return noteTypeService.findAll();
//    }
   @GetMapping("/search")
   public String search(@RequestParam("noteType") Long id,Model model){
       NoteType noteType=noteTypeService.findById(id);
       Iterable<Note> notes=noteService.findAllByNoteType(noteType);
       model.addAttribute("notes",notes);
       model.addAttribute("noteType",noteType);

       return "note/search";
   }

    @GetMapping("/show-note")
    public ModelAndView showNote(@PageableDefault(2) Pageable pageable){
        Page<Note> notes=noteService.showAll(pageable);
        Iterable<NoteType> noteTypes=noteTypeService.findAll();
        ModelAndView modelAndView=new ModelAndView("note/list");
        modelAndView.addObject("note",notes);
        modelAndView.addObject("noteType",noteTypes);
        return modelAndView;
    }

    @GetMapping("/show-createNote")
    public String showcreate(Model model){
        Iterable<NoteType> noteTypes=noteTypeService.findAll();
        model.addAttribute("note",new Note());
        model.addAttribute("noteType",noteTypes);
        return "note/create";
    }



    @PostMapping("/create-note")
    public String createNote(@ModelAttribute Note note,Model model){
        noteService.save(note);
        model.addAttribute("note",note);
        model.addAttribute("message","Them thanh cong");
        return "note/create";
    }

    @GetMapping("/show-editNote/{id}")
    public String showedit(@PageableDefault(2) @PathVariable Long id, Model model){
        Iterable<NoteType> noteTypes=noteTypeService.findAll();
        Note note=noteService.findById(id);
        model.addAttribute("note",note);
        model.addAttribute("noteType",noteTypes);
        return "note/edit";
    }

    @PostMapping("/edit-note")
    public String editNote(@ModelAttribute Note note,Model model){
        noteService.save(note);
        model.addAttribute("note",note);
        model.addAttribute("message","Sua thanh cong");
        return "note/edit";
    }

    @GetMapping("/show-deleteNote/{id}")
    public String showDelete(@PathVariable Long id,Model model){
        Note note=noteService.findById(id);
        model.addAttribute("note",note);
        return "note/delete";
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute Note note,Model model){
        Long id=note.getId();
        noteService.remove(id);
        model.addAttribute("note",note);
        model.addAttribute("message","Xoa thanh cong");
        return "note/delete";
    }
    @GetMapping("/show-viewNote/{id}")
    public String viewNote(@PathVariable Long id,Model model){
        Note note=noteService.findById(id);
        model.addAttribute("note",note);
        return "note/view";
    }


}
