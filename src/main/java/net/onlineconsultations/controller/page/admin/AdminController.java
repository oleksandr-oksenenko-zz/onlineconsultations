package net.onlineconsultations.controller.page.admin;

import net.onlineconsultations.controller.form.admin.SubjectForm;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminController {
    @Inject
    private SubjectService subjectService;

    @RequestMapping
    public String adminHome(Model model) {
        return "redirect:/admin/subjects";
    }

    @RequestMapping(value = "/subjects")
    public ModelAndView adminSubjects(Principal principal) {
        ModelAndView modelView = new ModelAndView("admin/index");
        modelView.addObject("pageType", "subjects");

        List<Subject> subjects = subjectService.getAllSubjects();
        modelView.addObject("subjects", subjects);

        return modelView;
    }

    @RequestMapping("/subjects/{id}/remove")
    public String subjectsRemove(@PathVariable("id") Long subjectId) {
        subjectService.remove(subjectId);

        return "redirect:/admin/subjects";
    }

    @RequestMapping("/subjects/{id}/edit")
    public String subjectsEdit(@PathVariable("id") Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);

        return "/admin/subject_edit";
    }

    @RequestMapping(value = "/subjects/{id}/edit", method = RequestMethod.POST)
    public String submitSubjectsEdit(SubjectForm subjectForm, BindingResult bindingResult) {

    }
}
