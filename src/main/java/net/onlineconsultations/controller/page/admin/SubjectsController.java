package net.onlineconsultations.controller.page.admin;

import net.onlineconsultations.controller.form.admin.SubjectForm;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = { "/admin/subjects", "/admin" })
public class SubjectsController {
    @Inject
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String adminSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);

        return "admin/subjects";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String subjectsAdd(Model model) {
        model.addAttribute("subject", new SubjectForm());

        return "admin/subjectForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String subjectsAddSubmit(@ModelAttribute("subject") @Valid SubjectForm subjectForm,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/subjectForm";
        }

        subjectService.save(new Subject(
                subjectForm.getName(),
                subjectForm.getDescription()
        ));

        redirectAttributes.addAttribute("message", "New subject has been successfully added.");

        return "redirect:/admin/subjects";
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String subjectsRemove(@PathVariable("id") Long subjectId,
                                 RedirectAttributes redirectAttributes) {
        subjectService.remove(subjectService.getSubjectById(subjectId));

        redirectAttributes.addAttribute("message", "Then chosen subject has been successfully deleted.");

        return "redirect:/admin/subjects";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String subjectsEdit(@PathVariable("id") Long subjectId,
                               Model model) {
        model.addAttribute("subject", SubjectForm.of(subjectService.getSubjectById(subjectId)));

        return "admin/subjectForm";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String subjectsEditSubmit(@PathVariable("id") Long subjectId,
                                     @ModelAttribute("subject") @Valid SubjectForm subjectForm,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/subjectForm";
        }

        Subject subject = subjectService.getSubjectById(subjectId);
        subject.setName(subjectForm.getName());
        subject.setDescription(subjectForm.getDescription());

        subjectService.merge(subject);

        redirectAttributes.addAttribute("message", "Subject has been successfully modified.");

        return "redirect:/admin/subjects";
    }
}
