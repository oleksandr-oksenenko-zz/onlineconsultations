package net.onlineconsultations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/subject" })
public class SubjectController {

    @RequestMapping(method = RequestMethod.GET)
    public String subjects() throws Exception {
        return "subjects";
    }

    @RequestMapping(value = "/{subject_id}", method = RequestMethod.GET)
    public String subject(@RequestParam("subject_id") Long subjectId)
            throws Exception {

        return "subject";
    }
}
