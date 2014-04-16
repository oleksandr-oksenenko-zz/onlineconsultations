package net.onlineconsultations.controller.page.admin;

import net.onlineconsultations.controller.form.admin.UserForm;
import net.onlineconsultations.domain.UserRole;
import net.onlineconsultations.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Arrays;

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

        return "admin/userForm";
    }
}
