package net.onlineconsultations.web.anonym.page;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.service.ConsultantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
@RequestMapping("/rate")
public class ConsultantRatingController {
    private static final String RATE_VIEW = "anonym/rate";

    @Inject
    private ConsultantService consultantService;

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public String ratePage(Model model,
                           @PathVariable("user_id") Long userId) {
        model.addAttribute("userId", userId);
        return RATE_VIEW;
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.POST)
    public String rateSubmit(@RequestParam("user_rating") Double userRating,
                             @PathVariable("user_id") Long userId) {
        Consultant consultant = consultantService.getById(userId);
        consultantService.recalculateConsultantRating(consultant, userRating);
        return "redirect:/";
    }
}
