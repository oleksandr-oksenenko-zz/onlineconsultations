package net.onlineconsultations.controller;

import java.util.ArrayList;
import java.util.List;

import net.onlineconsultations.domain.Role;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/subjects", "/" })
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(method = RequestMethod.GET)
    public String subjects(Model model) throws Exception {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @RequestMapping(value = "/{subject_id}", method = RequestMethod.GET)
    public String subject(@PathVariable("subject_id") Long subjectId,
            Model model) {

        Subject subject = this.subjectService.getSubjectById(subjectId);

        model.addAttribute("subject", subject);
        model.addAttribute("onlineConsultants", getOnlineConsultants());
        return "subject";
    }

    private List<User> getOnlineConsultants() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<User> onlineConsultants = new ArrayList<>();

        for (Object principal : allPrincipals) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                User user = userService
                        .getByUsername(userDetails.getUsername());

                if (user.getRole().equals(Role.ROLE_CONSULTANT)) {
                    onlineConsultants.add(user);
                }
            } else {
                throw new RuntimeException("Wrong principal type");
            }
        }
        return onlineConsultants;
    }
}
