package net.onlineconsultations.web.consultant.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/consultant")
public class WaitingRoomController {
    private static final Logger log = LoggerFactory.getLogger(WaitingRoomController.class);

    @RequestMapping(value = { "/waiting_room", ""}, method = RequestMethod.GET)
    public String waitingRoomPage() {
        return "consultant/waiting_room";
    }

}
