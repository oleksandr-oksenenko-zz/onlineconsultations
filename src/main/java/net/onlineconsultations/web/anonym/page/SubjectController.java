package net.onlineconsultations.web.anonym.page;

import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.service.SubjectService;
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
    private ConsultantService consultantService;

    @RequestMapping(method = RequestMethod.GET)
    public String subjects(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "subjects";
    }

    @RequestMapping(value = "/{subject_id}", method = RequestMethod.GET)
    public String subject(@PathVariable("subject_id") Long subjectId,
                          Model model) {

        Subject subject = subjectService.getById(subjectId);

        model.addAttribute("subject", subject);
        model.addAttribute("users", consultantService.getAvailableConsultants(subject));
        return "subject";
    }
}
