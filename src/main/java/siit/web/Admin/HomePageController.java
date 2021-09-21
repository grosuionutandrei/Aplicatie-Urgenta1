package siit.web.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.Alarm.AlarmService;


@Controller
public class HomePageController {
 @Autowired
    AlarmService alarmService;
    @GetMapping("/adminPage")
    public ModelAndView displayHomePage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/Admin/HomePage");
        mav.addObject("alarms",alarmService.getAlarms());
        return mav;
    }
}
