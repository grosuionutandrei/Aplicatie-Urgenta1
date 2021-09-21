package siit.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.IsolatedPeopleService;
import siit.sevices.PeopleService;
import siit.sevices.SearchByState;
import siit.sevices.SettlementService;
import javax.servlet.http.HttpSession;

@Controller
public class PeopleController {
    @Autowired
    PeopleService peopleService;
    @Autowired
    HttpSession session;
    @Autowired
    SettlementService settlementService;
    @Autowired
    IsolatedPeopleService isolatedPeopleService;
    @Autowired
    SearchByState searchByState;

    @GetMapping("/localnici")
    public ModelAndView displayPeoples(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Localnici");
        mav.addObject("peopleList",peopleService.getPeoples1(session));
        mav.addObject("Settlements",settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        mav.addObject("comuna" ,session.getAttribute("selected_commune").toString());
        return mav;
    }

    @GetMapping("/localnici/{settlement.name}")
    public ModelAndView displayPeople(@PathVariable("settlement.name") String name){
        ModelAndView mav =  new ModelAndView();
        mav.setViewName("LocalniciLocalitate");
        mav.addObject("peoples",peopleService.getPeoplesPerSettlement(name));
        return mav;
    }

    @PostMapping("/localnici/{settlement.name}")
    public ModelAndView displaySearchedValue(@RequestParam("name") String name,@PathVariable("settlement.name") String settlement){
        ModelAndView mav =  new ModelAndView();
        mav.setViewName("Localnic");
        mav.addObject("peoples",peopleService.getSearchResultForSettlement(name,settlement));
        return mav;
    }

    @PostMapping("/localnici")
    public ModelAndView displaySettlementPeoples(@RequestParam("name") String name){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Localnic");
        mav.addObject("peoples",peopleService.getSelectedPeoples(name));
        mav.addObject("peopleNumber",searchByState.numberOfStatePeople(name));
        mav.addObject("percentagePeople",searchByState.percentageOfStatePeople(name));
        return mav;
    }

    @GetMapping("/localnic/{people.id}/detalii")
        public ModelAndView displayDetailsAndChangeState(@PathVariable("people.id") String id){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("ChangeState");
            mav.addObject("states" , peopleService.getState());
            mav.addObject("people",peopleService.details(id));
            return mav;
        }
    @PostMapping("/localnic/{people.id}/detalii")
    public ModelAndView displayDetailsafterCahangingState(@RequestParam("selectState") String state , @RequestParam("observatii") String observatii){
        ModelAndView mav = new ModelAndView();
        peopleService.updatePeople(state,observatii);
        mav.addObject("peopleList",peopleService.getPeoples(session));
        mav.addObject("Settlements",settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        mav.setViewName("redirect:/localnici");
        return mav;
    }

}
