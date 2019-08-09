package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import com.codegym.service.NoteService;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteTypeController {
    @Autowired
    private NoteTypeService noteTypeService;

    @Autowired
    private NoteService noteService;
    @GetMapping("/show-notetype")
    public ModelAndView showNoteType( ){
        ModelAndView modelAndView=new ModelAndView("notetype/list");
        Iterable<NoteType> noteTypes=noteTypeService.findAll();
        modelAndView.addObject("notetype",noteTypes);
        return modelAndView;
    }

    @GetMapping("/show-createNoteType")
    public String showCreateNoteType(Model model){
        model.addAttribute("noteType",new NoteType());
        return "notetype/create";
    }

    @PostMapping("/create-Notetype")
    public String createNoteType(@ModelAttribute NoteType noteType,Model model){
        noteTypeService.save(noteType);
        model.addAttribute("noteType",new NoteType());
        model.addAttribute("message","Them thanh cong");
        return "notetype/create";
    }

    @GetMapping("/show-editNotetype/{id}")
    public String showeditNotetype(@PathVariable Long id,Model model){
        NoteType noteType=noteTypeService.findById(id);
        model.addAttribute("notetype",noteType);
        return "notetype/edit";
    }

    @PostMapping("/edit-Notetype")
    public String  editNotetype(@ModelAttribute NoteType noteType,Model model){
        noteTypeService.save(noteType);
        model.addAttribute("notetype",noteType);
        model.addAttribute("message","Sua thanh cong");
        return "notetype/edit";
    }
    @GetMapping("/show-deleteNotetype/{id}")
    public String showdeleteNotetype(@PathVariable Long id,Model model){
        NoteType noteType=noteTypeService.findById(id);
        model.addAttribute("notetype",noteType);
        return "notetype/delete";
    }


    @PostMapping("/delete-Notetype")
    public String  deleteNotetype(@ModelAttribute NoteType noteType,Model model){
        Long id=noteType.getId();
        noteTypeService.remove(id);
        model.addAttribute("notetype",noteType);
        model.addAttribute("message","Xoa thanh cong");
        return "notetype/delete";
    }

    @GetMapping("/show-viewNotetype/{id}")
    public String showviewNotetype(@PathVariable Long id,Model model){
        NoteType noteType=noteTypeService.findById(id);
        Iterable<Note> notes= noteService.findAllByNoteType(noteType);
        model.addAttribute("notetype",noteType);
        model.addAttribute("note",notes);
        return "notetype/view";
    }

}
