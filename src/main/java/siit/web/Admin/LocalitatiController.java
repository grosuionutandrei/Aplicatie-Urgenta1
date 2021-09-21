package siit.web.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.SettlementService;

@Controller
public class LocalitatiController {
@Autowired
    SettlementService settlementService;

    @GetMapping("/localitati")
    public ModelAndView displayLocalitati(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/Admin/Localitati");
        mav.addObject("settlements", settlementService.getUserSettlementView());
        return mav;
    }
    @PostMapping("/localitati")
    public ModelAndView insertNewSettlement(@RequestParam("nameLoc") String localitate, @RequestParam("nameCom") String comuna){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/localitati");
        settlementService.insertNewSettlement(localitate,comuna);

        return mav;
    }

}
