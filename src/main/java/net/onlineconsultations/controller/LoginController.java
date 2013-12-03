package net.onlineconsultations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(
            @RequestParam(value = "error", required = false) Boolean isError,
            Model model) {
        if (isError != null) {
            model.addAttribute("error", isError);
        }
        return "login";
    }
}
