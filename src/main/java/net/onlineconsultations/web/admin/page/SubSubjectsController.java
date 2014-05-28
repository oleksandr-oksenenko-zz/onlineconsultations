package net.onlineconsultations.web.admin.page;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.service.SubSubjectService;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.web.admin.form.SubSubjectForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/sub_subjects")
public class SubSubjectsController {
    @Inject
    private SubjectService subjectService;

    @Inject
    private SubSubjectService subSubjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String subSubjectsHome(Model model) {
        model.addAttribute("subSubjects", subSubjectService.getAll());

        return "admin/subSubjects";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String subSubjectsAdd(Model model) {
        model.addAttribute("subSubject", new SubSubjectForm());
        model.addAttribute("subjects", subjectService.getAll());

        return "admin/subSubjectForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String subSubjectsAddSubmit(@ModelAttribute("subSubject") @Valid SubSubjectForm subSubjectForm,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjects", subjectService.getAll());
            return "admin/subSubjectForm";
        }

        subSubjectService.save(new SubSubject(
                subSubjectForm.getName(),
                subSubjectForm.getDescription(),
                subjectService.getById(subSubjectForm.getParentSubjectId())
        ));

        return "redirect:/admin/sub_subjects";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String subSubjectsEdit(@PathVariable("id") Long subSubjectId,
                                  Model model) {
        SubSubject subSubject = subSubjectService.getById(subSubjectId);

        model.addAttribute("subSubject", new SubSubjectForm(
                subSubject.getParentSubject().getId(),
                subSubject.getName(),
                subSubject.getDescription())
        );
        model.addAttribute("subjects", subjectService.getAll());

        return "admin/subSubjectForm";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String subSubjectsEditSubmit(@PathVariable("id") Long subSubjectId,
                                        @ModelAttribute("subSubject") SubSubjectForm subSubjectForm,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjects", subjectService.getAll());
            return "admin/subSubjectForm";
        }

        SubSubject subSubject = subSubjectService.getById(subSubjectId);
        subSubject.setName(subSubjectForm.getName());
        subSubject.setDescription(subSubjectForm.getDescription());
        subSubject.setParentSubject(subjectService.getById(subSubjectForm.getParentSubjectId()));

        subSubjectService.merge(subSubject);

        return "redirect:/admin/sub_subjects";
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String subSubjectsRemove(@PathVariable("id") Long subSubjectId) {
        subSubjectService.remove(subSubjectId);

        return "redirect:/admin/sub_subjects";
    }
}
