package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.Alarm.InformatiiUtileService;
import siit.sevices.ContactService;

import javax.servlet.http.HttpSession;

@Controller
public class ContacteUtileController {
@Autowired
    InformatiiUtileService informatiiUtileService;
@Autowired
    ContactService contactService;
@Autowired
    HttpSession session;


    @GetMapping("/contacteUtile")
    public ModelAndView displayInfUtilePage(){
        ModelAndView mav = new ModelAndView();

        mav.addObject("pompieri",contactService.pompieriContacts(session.getAttribute("selected_commune").toString()));
        mav.addObject("primarie" ,contactService.primarieContacts(session.getAttribute("selected_commune").toString()));

        mav.setViewName("ContacteUtile");
        return mav;
    }

}
