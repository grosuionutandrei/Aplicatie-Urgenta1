package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.*;

import javax.servlet.http.HttpSession;

@Controller
public class IsolatedPeopleController {
    @Autowired
    IsolatedPeopleService isolatedPeopleService;
    @Autowired
    PeopleService peopleService;
    @Autowired
    ComunneService comunneService;
    @Autowired
    SettlementService settlementService;
    @Autowired
    HttpSession session;

    @GetMapping("/listaIzolati")
    public ModelAndView displayIsolatedPeople() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("Settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        mav.addObject("isolated", isolatedPeopleService.getIsolatedPeople(session.getAttribute("selected_commune").toString()));
        mav.setViewName("Localniciizolati");
        return mav;
    }

    @GetMapping("/localniciIzolati/{settlement.name}")
    public ModelAndView dispalaySelectedPeoplePerSettlement(@PathVariable("settlement.name") String localitate) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LocalniciIzolatiLocalitate");
        mav.addObject("peoples", isolatedPeopleService.getIsolatedPeoplePerSettlement(localitate));

        return mav;
    }

    @GetMapping("/listaIzolati/{people.id}/detalii")
    public ModelAndView displaySelectedIsolatedPeople(@PathVariable("people.id") String id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ChangeStateIzolati");
        mav.addObject("states", peopleService.getState());
        mav.addObject("people", isolatedPeopleService.displaySelectedPeople(id));
        return mav;
    }

    @PostMapping("/listaIzolati/{people.id}/detalii")
    public ModelAndView updateStetIsolatedPeople(@RequestParam("selectState") String select, @RequestParam("observatii") String observatii) {
        ModelAndView mav = new ModelAndView();
        isolatedPeopleService.updateIsolatedPeople(select, observatii);
        mav.setViewName("redirect:/listaIzolati");
        return mav;
    }

    @PostMapping("/listaIzolati")
    public ModelAndView displaySearchedValue(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LocalnicIzolat");
        mav.addObject("peoples", isolatedPeopleService.displaySearchedIsolatedPeople(name));
        return mav;
    }

    @PostMapping("/localniciIzolati/{settlement.name}")
    public ModelAndView displaySearchedPeopleIsolatedPerSettlement(@RequestParam("name") String search, @PathVariable("settlement.name") String settlement) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LocalnicIzolat");
        mav.addObject("peoples", isolatedPeopleService.displaySearchedIsolatedPeoplePerSettlement(search, settlement));
        return mav;
    }
}





