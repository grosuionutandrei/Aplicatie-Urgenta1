package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.Alarm.InformatiiUtileService;
import siit.sevices.ContactService;
import siit.sevices.SettlementService;

import javax.servlet.http.HttpSession;

@Controller
public class ContactePrimarieController {
    @Autowired
    InformatiiUtileService informatiiUtileService;
    @Autowired
    ContactService contactService;
    @Autowired
    HttpSession session;
    @Autowired
    SettlementService settlementService;

    @GetMapping("/contactePrimarie")
    public ModelAndView displayContacts(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/contacteUtilePrimarie");
        mav.addObject("pompieri",contactService.pompieriContacts(session.getAttribute("selected_commune").toString()));
        mav.addObject("primarie" ,contactService.primarieContacts(session.getAttribute("selected_commune").toString()));
        return mav;
    }

    @GetMapping("/addContact")
    public ModelAndView displayAddForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/AddContact");
        mav.addObject("settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        return mav;
    }
    @PostMapping("/addContact")
    public ModelAndView addNewContact(@RequestParam("name") String name,@RequestParam("phoneNumber") String phoneNumber,
                                      @RequestParam("rol") String rol,@RequestParam("settlement") String settlement){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/contactePrimarie");
        contactService.addContact(name,phoneNumber,rol,settlement);
        return mav;
    }
    @GetMapping("/contactePrimarie/{contact.id}/delete")
    public ModelAndView deleteContact(@PathVariable("contact.id") int id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/contactePrimarie");
        contactService.deleteContact(id);
        return mav;
    }
}
