package siit.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Alimente.PerecheNumeInteger;
import siit.sevices.Alimente.AlimenteService;
import siit.sevices.Twilio.SmsRequest;
import siit.sevices.Twilio.TwilioService;
import java.util.List;

@Controller
public class AjutoareAlimentareController {
    @Autowired
    AlimenteService alimenteService;
    @Autowired
    TwilioService twilioService;



    @GetMapping("/ajutoareAlimentare")
    public ModelAndView displayAjutoareAlimentare() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("days", alimenteService.displayDays());
        mav.setViewName("AjutoareAlimentare");
        return mav;
    }

    @PostMapping("/ajutoareAlimentare")
    public ModelAndView dispalyAjutoare(@RequestParam("numarPersoane") Integer numarPersoane, @RequestParam("days") int days) {
        ModelAndView mav = new ModelAndView();

            if(numarPersoane!=null ) {
                List<PerecheNumeInteger> alimentsNecessary = alimenteService.displayFoodNecessary(alimenteService.getList(), days);
                List<PerecheNumeInteger> totalAliments = alimenteService.totalAlimentsNecessary(alimentsNecessary, days, numarPersoane);
                mav.addObject("alimente", alimentsNecessary);
                mav.addObject("day", days);
                SmsRequest smsRequest = new SmsRequest(days, numarPersoane, totalAliments);
                System.out.println(smsRequest.getPhoneNumber());
                System.out.println(smsRequest.getMessage());
                //twilioService.sendSms(smsRequest);
                mav.setViewName("AlimentePage");
            }else {
                String error = "Introdu numarul de persoane";
                mav.addObject("error", error);
                mav.setViewName("AjutoareAlimentare");
            }

        return mav;

    }


}
