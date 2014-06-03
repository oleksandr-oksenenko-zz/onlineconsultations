package net.onlineconsultations.web.anonym.page;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.service.SubSubjectService;
import net.onlineconsultations.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private static final String SUBJECTS_VIEW = "anonym/subjects";
    private static final String SUB_SUBJECT_VIEW = "anonym/subSubjects";

    @Inject
    private SubjectService subjectService;

    @Inject
    private SubSubjectService subSubjectService;

    @Inject
    private ConsultantService consultantService;

    @RequestMapping(method = RequestMethod.GET)
    public String subjects(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return SUBJECTS_VIEW;
    }

    @RequestMapping(value = "/{subject_id}", method = RequestMethod.GET)
    public String subject(@PathVariable("subject_id") Long subjectId,
                          Model model) {
        Subject subject = subjectService.getById(subjectId);
        List<SubSubject> subSubjects = subSubjectService.getBySubjectId(subjectId);

        model.addAttribute("subSubjects", subSubjects);
        model.addAttribute("users", consultantService.getAvailableConsultants(subject));
        return SUB_SUBJECT_VIEW;
    }
}
