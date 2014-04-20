package net.onlineconsultations.web.consultant.rest;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.web.consultant.rest.model.SubSubjectInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/consultant")
public class ConsultantResource {
    @Inject
    private SubjectService subjectService;

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
}