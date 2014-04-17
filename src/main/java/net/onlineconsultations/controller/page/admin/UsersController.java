package net.onlineconsultations.controller.page.admin;

import net.onlineconsultations.controller.form.admin.UserForm;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.domain.UserRole;
import net.onlineconsultations.service.UserService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "admin/users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String usersAdd(Model model) {
        model.addAttribute("user", new UserForm());
        model.addAttribute("userRoles", Arrays.asList(UserRole.values()));
        model.addAttribute("mode", "add");

        return "admin/userForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String usersAddSubmit(@Valid @ModelAttribute("user") UserForm userForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "add");
            return "admin/userForm";
        }

        userService.save(new User(
                userForm.getUsername(),
                userForm.getPassword(),
                userForm.getFirstName(),
                userForm.getMiddleName(),
                userForm.getLastName(),
                userForm.getQualification(),
                userForm.getUserRole()
        ));

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String usersEdit(@PathVariable("id") Long userId,
                            Model model) {
        User user = userService.getById(userId);

        model.addAttribute("user", UserForm.of(user));
        model.addAttribute("userRoles", Arrays.asList(UserRole.values()));
        model.addAttribute("mode", "edit");

        return "admin/userForm";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String usersEditSubmit(@PathVariable("id") Long userId,
                                  @Valid @ModelAttribute("user") UserForm userForm,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "admin/userForm";
        }

        userService.merge(new User(
                userId,
                userForm.getUsername(),
                userForm.getPassword(),
                userForm.getUserRole(),
                userForm.getFirstName(),
                userForm.getMiddleName(),
                userForm.getLastName(),
                userForm.getQualification(),
                new HashSet<SubSubject>()
        ));

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String usersRemove(@PathVariable("id") Long userId) {
        userService.remove(userId);

        return "redirect:/admin/users";
    }
}
