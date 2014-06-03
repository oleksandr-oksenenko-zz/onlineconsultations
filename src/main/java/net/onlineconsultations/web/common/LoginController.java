package net.onlineconsultations.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final String LOGIN_VIEW = "anonym/login";

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) boolean error, Model model) {
        model.addAttribute("error", error);
        return LOGIN_VIEW;
    }
}