package net.onlineconsultations.web.consultant.rest;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.service.UserService;
import net.onlineconsultations.web.consultant.rest.model.ConsultantStatus;
import net.onlineconsultations.web.consultant.rest.model.SubSubjectInfo;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/consultant")
public class ConsultantResource {
    @Inject
    private SubjectService subjectService;

    @Inject
    private UserService userService;

    @RequestMapping(value = "/subjects/{id}/sub_subjects", method = RequestMethod.POST)
    @ResponseBody
    public List<SubSubjectInfo> getAllSubSubjectsBySubjectId(@PathVariable("id") Long subjectId) {
        List<SubSubject> subSubjects= subjectService.getSubSubjectsBySubjectId(subjectId);

        List<SubSubjectInfo> response = new ArrayList<>(subSubjects.size());
        for (SubSubject subSubject : subSubjects) {
            response.add(new SubSubjectInfo(subSubject.getId(), subSubject.getName()));
        }

        return response;
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public HttpEntity<String> setConsultantStatus(Principal principal,
                                                  @RequestParam("status") ConsultantStatus status) {
        User user = userService.findByUsername(principal.getName());

        switch (status) {
            case WAITING_FOR_USERS:
                user.setWaitingForChat(true);
                break;
            case NOT_WAITING_FOR_USERS:
                user.setWaitingForChat(false);
                break;
        }

        userService.merge(user);

        return new HttpEntity<>("OK");
    }
}