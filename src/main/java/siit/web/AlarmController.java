package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Alarm;
import siit.sevices.Alarm.AlarmService;
import siit.sevices.PeopleService;
import siit.sevices.SettlementService;

import javax.servlet.http.HttpSession;

@Controller
public class AlarmController {
    @Autowired
    PeopleService peopleService;
    @Autowired
    SettlementService settlementService;
    @Autowired
    AlarmService alarmService;
    @Autowired
    HttpSession session;
    @PostMapping("homePage1")
    public ModelAndView startAlarm(@RequestParam("alarm") boolean alarm) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/localnici");
        alarmService.insertOrUpdate(alarmService.getAlarm(),alarm);
        alarmService.updatePeopleState(alarm,session);
        mav.addObject("Settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        mav.addObject("peopleList", peopleService.getPeoples(session));
        return mav;
    }

}
