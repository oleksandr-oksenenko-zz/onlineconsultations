package net.onlineconsultations.web.anonym.page;

import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Inject
    private SubjectService subjectService;

    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String subjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @RequestMapping(value = "/{subject_id}", method = RequestMethod.GET)
    public String subject(@PathVariable("subject_id") Long subjectId,
                          Model model) {

        Subject subject = subjectService.getSubjectById(subjectId);

        model.addAttribute("subject", subject);
        model.addAttribute("users", userService.getWaitingConsultantsBySubject(subject));
        return "subject";
    }
}
