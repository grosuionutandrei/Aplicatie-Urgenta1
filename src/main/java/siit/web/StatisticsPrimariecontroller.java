package siit.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.Alarm.InformatiiUtileService;
import siit.sevices.Statistics.Statistics;

@Controller
public class StatisticsPrimariecontroller {

    @Autowired
    InformatiiUtileService informatiiUtileService;
    @Autowired
    Statistics statistics;

    @GetMapping("/statisticiPrimarie")
    public ModelAndView displayStatistics() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/Statistici");
        mav.addObject("chartValues", informatiiUtileService.chartValuesState());
        mav.addObject("alimentes", statistics.alimentes());
        return mav;
    }
}
