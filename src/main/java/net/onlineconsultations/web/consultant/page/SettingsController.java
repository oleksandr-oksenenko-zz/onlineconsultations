package net.onlineconsultations.web.consultant.page;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.service.SubSubjectService;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.web.consultant.form.SubSubjectForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/consultant")
public class SettingsController {
    private static final Logger log = LoggerFactory.getLogger(SettingsController.class);

    private static final String SETTINGS_VIEW = "consultant/settings";
    private static final String SUB_SUBJECTS_FORM_VIEW = "consultant/subSubjectForm";

    @Inject
    private SubjectService subjectService;

    @Inject
    private SubSubjectService subSubjectService;

    @Inject
    private ConsultantService consultantService;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsPage(Principal principal,
                               Model model) {
        Consultant consultant = consultantService.getByUsername(principal.getName());

        model.addAttribute("subSubjects", subSubjectService.getByConsultant(consultant));

        return SETTINGS_VIEW;
    }

    @RequestMapping(value = "/settings/sub_subjects/add", method = RequestMethod.GET)
    public String settingsAddSubSubject(Model model) {
        List<Subject> subjects = subjectService.getAll();

        model.addAttribute("subSubject", new SubSubjectForm());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("subSubjects", subSubjectService.getBySubjectId(subjects.get(0).getId()));

        return SUB_SUBJECTS_FORM_VIEW;
    }

    @RequestMapping(value = "/settings/sub_subjects/add", method = RequestMethod.POST)
    public String settingsAddSubSubject(Principal principal,
                                        @Valid @ModelAttribute("subSubject") SubSubjectForm subSubjectForm,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subSubjects", subSubjectService.getBySubjectId(subSubjectForm.getSubjectId()));
            model.addAttribute("subjects", subjectService.getAll());

            return SUB_SUBJECTS_FORM_VIEW;
        }

        try {
            Consultant consultant = consultantService.getByUsername(principal.getName());
            SubSubject subSubject = subSubjectService.getById(subSubjectForm.getSubSubjectId());

            consultantService.linkSubSubjectToConsultant(consultant, subSubject);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue(
                    "subSubjectId",
                    "error.user.subSubjectId.nonunique",
                    "Sub subject is already added to this user."
            );

            model.addAttribute("subSubjects", subSubjectService.getBySubjectId(subSubjectForm.getSubjectId()));
            model.addAttribute("subjects", subjectService.getAll());

            log.error("Exception while adding subsubject to user", e);

            return SUB_SUBJECTS_FORM_VIEW;
        }

        return "redirect:/consultant/settings";
    }

    @RequestMapping(value = "/settings/sub_subjects/{id}/remove", method = RequestMethod.GET)
    public String settingsRemoveSubSubject(Principal principal,
                                           @PathVariable("id") Long subSubjectId,
                                           Model model) {
        Consultant consultant = consultantService.getByUsername(principal.getName());
        SubSubject subSubject = subSubjectService.getById(subSubjectId);

        consultantService.unlinkSubSubjectFromConsultant(consultant, subSubject);

        return "redirect:/consultant/settings";
    }
}
