package siit.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.HandicapPeopleService.HandicapPeopleService;
import siit.sevices.PeopleService;
import siit.sevices.SettlementService;
import javax.servlet.http.HttpSession;

@Controller
public class HandicapPeopleController {
    @Autowired
    HandicapPeopleService handicapPeopleService;
    @Autowired
    SettlementService settlementService;
    @Autowired
    HttpSession session;
    @Autowired
    PeopleService peopleService;


    @GetMapping("/listaHandicap")
    public ModelAndView displayHandicapPeople() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Handicap");
        mav.addObject("handicap", handicapPeopleService.displayHandicapPeople(session));
        mav.addObject("Settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        return mav;
    }

    @PostMapping("/listaHandicap")
    public ModelAndView displaySearchedHandicapPeople(@RequestParam("name") String search) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LocalnicHandicap");
        mav.addObject("handicap", handicapPeopleService.displaySearchedHandicapPeople(search,session));

        return mav;
    }

    @GetMapping("/listaHandicap/{people.id}/detalii")
    public ModelAndView changeStateHandicap(@PathVariable("people.id") String id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ChangeStateHandicap");
        mav.addObject("states",peopleService.getState());
        mav.addObject("Settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        mav.addObject("people", handicapPeopleService.displayDetailsPerHandicapPeople(id,session));
        return mav;
    }

    @PostMapping("/listaHandicap/{people.id}/detalii")
    public ModelAndView changeStateHandicap(@RequestParam("selectState") String state, @RequestParam("observatii") String observatii) {
        ModelAndView mav = new ModelAndView();
        handicapPeopleService.updateHandicapState(state, observatii);
        mav.setViewName("redirect:/listaHandicap");
        return mav;
    }

    @GetMapping("/listaHandicap/{settlement.name}")
    public ModelAndView displayHandicapPerSettlement(@PathVariable("settlement.name") String settlement) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("HandicapLocalitate");
        mav.addObject("handicap", handicapPeopleService.displayHandicapPeoplePerSettlement(settlement));
        return mav;
    }

    @PostMapping("/listaHandicap/{settlement.name}")
    public ModelAndView displaySearchedHandiacpPerSettlement(@RequestParam("name") String search, @PathVariable("settlement.name") String settlement) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("handicap", handicapPeopleService.displaySearchedHandicapPerSettlement(search, settlement));
        mav.setViewName("HandicapLocalnic");
        return mav;
    }
}
