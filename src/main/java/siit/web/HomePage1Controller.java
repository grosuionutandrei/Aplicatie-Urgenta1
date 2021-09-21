package siit.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.*;
import siit.sevices.Alarm.AlarmService;

import javax.servlet.http.HttpSession;

@Controller
public class HomePage1Controller {
     @Autowired
    SettlementService settlementService;
    @Autowired
    HttpSession session;
    @Autowired
    ContactService contactService;
    @Autowired
    AlarmService alarmService;

    @GetMapping("/homePage1")
    public ModelAndView displayotherPage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("HomePage1");
        mav.addObject("settlements" ,settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        System.out.println(alarmService.getAlarm());
        mav.addObject("alarm", alarmService.getAlarm());
        return mav;
    }

}
