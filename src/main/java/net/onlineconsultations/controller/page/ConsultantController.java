package net.onlineconsultations.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {

    @RequestMapping(method = RequestMethod.GET)
    public String consultantPage() {
        return "consultant";
    }
}
