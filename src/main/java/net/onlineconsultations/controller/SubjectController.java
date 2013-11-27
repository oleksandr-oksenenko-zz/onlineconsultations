package net.onlineconsultations.controller;

import net.onlineconsultations.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/subjects", "/" })
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String subjects(Model model) throws Exception {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @RequestMapping(value = "/{subject_id}", method = RequestMethod.GET)
    public String subject(@PathVariable("subject_id")
    Long subjectId, Model model)
            throws Exception {
        model.addAttribute("subject", subjectService.getSubjectById(subjectId));
        return "subject";
    }
}
