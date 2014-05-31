package net.onlineconsultations.web.consultant.rest;

import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.service.SubSubjectService;
import net.onlineconsultations.service.SubjectService;
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
@RequestMapping("/api/consultant")
public class ConsultantResource {
    @Inject
    private SubjectService subjectService;

    @Inject
    private SubSubjectService subSubjectService;

    @Inject
    private ConsultantService consultantService;

    @RequestMapping(value = "/subjects/{id}/sub_subjects", method = RequestMethod.POST)
    @ResponseBody
    public List<SubSubjectInfo> getAllSubSubjectsBySubjectId(@PathVariable("id") Long subjectId) {
        List<SubSubject> subSubjects = subSubjectService.getBySubjectId(subjectId);

        List<SubSubjectInfo> response = new ArrayList<>(subSubjects.size());
        for (SubSubject subSubject : subSubjects) {
            response.add(
                    new SubSubjectInfo(subSubject.getId(), subSubject.getName())
            );
        }

        return response;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public HttpEntity<String> setConsultantStatus(Principal principal,
                                                  @RequestParam("status") ConsultantStatus status) {
        Consultant consultant = consultantService.getByUsername(principal.getName());

        switch (status) {
            case WAITING_FOR_USERS:
                consultantService.changeConsultantStatus(consultant, true);
                break;
            case NOT_WAITING_FOR_USERS:
                consultantService.changeConsultantStatus(consultant, false);
                break;
        }

        return new HttpEntity<>("OK");
    }
}