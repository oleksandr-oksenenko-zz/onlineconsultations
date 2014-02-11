package net.onlineconsultations.controller.page;

import net.onlineconsultations.controller.page.model.UserLoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(@Valid UserLoginInfo userLoginInfo,
                            Model model,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", true);
        }

        return "login";
    }
}
