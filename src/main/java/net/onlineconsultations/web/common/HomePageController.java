package net.onlineconsultations.web.common;

import net.onlineconsultations.domain.User;
import net.onlineconsultations.domain.UserRole;
import net.onlineconsultations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.security.Principal;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomePageController {
    private static final Logger log = LoggerFactory.getLogger(HomePageController.class);

    @Inject
    private UserService userService;

    @RequestMapping
    public String homePage(Principal principal) {
        if (principal == null) {
            return "redirect:/subjects";
        } else {
            User user = userService.findByUsername(principal.getName());
            if (user.getRole() == UserRole.ROLE_CONSULTANT) {
                return "redirect:/consultant";
            } else if (user.getRole() == UserRole.ROLE_ADMIN) {
                return "redirect:/admin";
            }
        }

        return "redirect:/error";
    }

}
