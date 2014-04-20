package net.onlineconsultations.web.consultant.page;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.service.UserService;
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

    @Inject
    private SubjectService subjectService;

    @Inject
    private UserService userService;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsPage(Principal principal,
                               Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("subSubjects", subjectService.getSubSubjectsByUser(user));

        return "consultant/settings";
    }

    @RequestMapping(value = "/settings/sub_subjects/add", method = RequestMethod.GET)
    public String settingsAddSubSubject(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();

        model.addAttribute("subSubject", new SubSubjectForm());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("subSubjects", subjectService.getSubSubjectsBySubjectId(subjects.get(0).getId()));

        return "consultant/subSubjectForm";
    }

    @RequestMapping(value = "/settings/sub_subjects/add", method = RequestMethod.POST)
    public String settingsAddSubSubject(Principal principal,
                                        @Valid @ModelAttribute("subSubject") SubSubjectForm subSubjectForm,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subSubjects", subjectService.getSubSubjectsBySubjectId(subSubjectForm.getSubjectId()));
            model.addAttribute("subjects", subjectService.getAllSubjects());

            return "consultant/subSubjectForm";
        }

        try {
            User user = userService.findByUsername(principal.getName());
            SubSubject subSubject = subjectService.getSubSubjectById(subSubjectForm.getSubSubjectId());

            userService.addSubSubjectToUser(user, subSubject);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("subSubjectId",
                    "error.user.subSubjectId.nonunique",
                    "Sub subject is already added to this user.");


            model.addAttribute("subSubjects", subjectService.getSubSubjectsBySubjectId(subSubjectForm.getSubjectId()));
            model.addAttribute("subjects", subjectService.getAllSubjects());

            log.error("Exception while adding subsubject to user", e);

            return "consultant/subSubjectForm";
        }

        return "redirect:/consultant/settings";
    }

    @RequestMapping(value = "/settings/sub_subjects/{id}/remove", method = RequestMethod.GET)
    public String settingsRemoveSubSubject(Principal principal,
                                           @PathVariable("id") Long subSubjectId,
                                           Model model) {
        User user = userService.findByUsername(principal.getName());
        SubSubject subSubject = subjectService.getSubSubjectById(subSubjectId);

        try {
            userService.removeSubSubjectFromUser(user, subSubject);
        } catch (DataAccessException e) {
            model.addAttribute("reason", e.getMessage());
            return "error";
        }

        return "redirect:/consultant/settings";
    }
}
