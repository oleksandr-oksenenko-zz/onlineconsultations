package net.onlineconsultations.web.consultant.page;

import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/consultant")
public class WaitingRoomController {
    private static final Logger log = LoggerFactory.getLogger(WaitingRoomController.class);

    @RequestMapping(value = { "/waiting_room", ""}, method = RequestMethod.GET)
    public String waitingRoomPage() {
        return "consultant/waiting_room";
    }

}
