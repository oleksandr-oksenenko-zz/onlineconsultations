package net.onlineconsultations.web.admin.page;

import net.onlineconsultations.domain.Administrator;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.AdministratorService;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.service.UserService;
import net.onlineconsultations.web.admin.form.AdministratorForm;
import net.onlineconsultations.web.admin.form.ConsultantForm;
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

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    private static final String ADMINISTRATOR_FORM_VIEW = "admin/user/administratorForm";
    private static final String CONSULTANT_FORM_VIEW = "admin/user/consultantForm";
    private static final String USERS_VIEW = "admin/user/users";

    @Inject
    private UserService userService;

    @Inject
    private ConsultantService consultantService;

    @Inject
    private AdministratorService administratorService;

    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());

        return USERS_VIEW;
    }

    @RequestMapping(value = "/add_admin", method = RequestMethod.GET)
    public String addAdminPage(Model model) {
        model.addAttribute("administrator", new AdministratorForm());
        model.addAttribute("mode", "add");

        return ADMINISTRATOR_FORM_VIEW;
    }

    @RequestMapping(value = "/add_admin", method = RequestMethod.POST)
    public String addAdminSubmit(@Valid @ModelAttribute("administrator") AdministratorForm administratorForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "add");
            return ADMINISTRATOR_FORM_VIEW;
        }

        try {
            userService.save(new Administrator(
                    administratorForm.getUsername(),
                    administratorForm.getUsername()
            ));
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username",
                    "error.user.username.nonunique",
                    "There is a user with the same username.");
            model.addAttribute("mode", "add");
            return ADMINISTRATOR_FORM_VIEW;
        }

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/add_consultant", method = RequestMethod.GET)
    public String usersAddConsultant(Model model) {
        model.addAttribute("consultant", new ConsultantForm());
        model.addAttribute("mode", "add");

        return CONSULTANT_FORM_VIEW;
    }

    @RequestMapping(value = "/add_consultant", method = RequestMethod.POST)
    public String usersAddConsultantSubmit(@Valid @ModelAttribute("consultant") ConsultantForm consultantForm,
                                           BindingResult bindingResult,
                                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "add");
            return CONSULTANT_FORM_VIEW;
        }

        try {
            userService.save(new Consultant(
                    consultantForm.getUsername(),
                    consultantForm.getPassword(),
                    consultantForm.getFirstName(),
                    consultantForm.getMiddleName(),
                    consultantForm.getLastName(),
                    consultantForm.getQualification()
            ));
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username",
                    "error.user.username.nonunique",
                    "There is a user with the same username.");
            model.addAttribute("mode", "add");
            return CONSULTANT_FORM_VIEW;
        }

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String usersEdit(@PathVariable("id") Long userId,
                            Model model) {
        model.addAttribute("mode", "edit");

        User user = userService.getById(userId);

        switch (user.getUserRole()) {
            case ROLE_CONSULTANT:
                Consultant consultant = consultantService.getById(userId);
                model.addAttribute("consultant", ConsultantForm.of(consultant));
                return CONSULTANT_FORM_VIEW;

            case ROLE_ADMIN:
                Administrator administrator = administratorService.getById(userId);
                model.addAttribute("administrator", AdministratorForm.of(administrator));
                return ADMINISTRATOR_FORM_VIEW;

            default:
                throw new RuntimeException("Unexpected user role");
        }
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = { "consultant" })
    public String editConsultantSubmit(@PathVariable("id") Long userId,
                                       @Valid @ModelAttribute("consultant") ConsultantForm consultantForm,
                                       BindingResult bindingResult,
                                       Model model) {
        Consultant consultant = consultantService.getById(userId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return CONSULTANT_FORM_VIEW;
        }
        User modifiedUser = new Consultant(
                consultant.getUsername(),
                (consultantForm.getPassword() == null ? "" : consultantForm.getPassword()),
                consultantForm.getFirstName(),
                consultantForm.getMiddleName(),
                consultantForm.getLastName(),
                consultantForm.getQualification()
        );
        modifiedUser.setId(userId);

        try {
            userService.merge(modifiedUser);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username",
                    "error.user.username.nonunique",
                    "There is a user with the same username.");
            model.addAttribute("mode", "edit");
            return CONSULTANT_FORM_VIEW;
        }

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = { "administrator" })
    public String editAdminSubmit(@PathVariable("id") Long userId,
                                  @Valid @ModelAttribute("administrator") AdministratorForm administratorForm,
                                  BindingResult bindingResult,
                                  Model model) {
        Administrator administrator = administratorService.getById(userId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return ADMINISTRATOR_FORM_VIEW;
        }

        User modifiedUser = new Administrator(
                administrator.getUsername(),
                (administratorForm.getPassword() == null ? "" : administratorForm.getPassword())
        );
        modifiedUser.setId(userId);

        try {
            userService.merge(modifiedUser);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username",
                    "error.user.username.nonunique",
                    "There is a user with the same username.");
            model.addAttribute("mode", "edit");
            return ADMINISTRATOR_FORM_VIEW;
        }

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String usersRemove(@PathVariable("id") Long userId) {
        userService.remove(userId);

        return "redirect:/admin/users";
    }
}
