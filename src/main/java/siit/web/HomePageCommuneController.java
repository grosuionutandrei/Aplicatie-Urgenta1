package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import siit.model.PeopleAdapter;
import siit.model.PeopleAdapterEdit;
import siit.model.Settlement;
import siit.sevices.PeopleAdapter.PeopleAdapterEditService;
import siit.sevices.PeopleAdapter.PeopleAdapterService;
import siit.sevices.PeopleService;
import siit.sevices.SettlementService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@Controller
public class HomePageCommuneController {
    @Autowired
    PeopleService peopleService;
    @Autowired
    SettlementService settlementService;
    @Autowired
    HttpSession session;
    @Autowired
    PeopleAdapterService peopleAdapterService;
    @Autowired
    PeopleAdapterEditService adapterEditService;


    @GetMapping("/homePage2")
    public ModelAndView displayHomePage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/HomePage2");
        mav.addObject("peopleList", peopleService.getPeoples1(session));
        mav.addObject("Settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        return mav;
    }

    @PostMapping("/homePage2")
    public ModelAndView displaySearch(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/CommuneSearchView");
        mav.addObject("peoples", peopleService.getSelectedPeoples(name));
        return mav;
    }

    @GetMapping("/homePage2/{settlement.name}")
    public ModelAndView displayPeoplePerSettlementCommuneWorker(@PathVariable("settlement.name") String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/HomePage2LocalniciLocalitate");
        mav.addObject("peoples", peopleService.getPeoplesPerSettlement(name));
        return mav;
    }

    @PostMapping("/homePage2/{settlement.name}")
    public ModelAndView displaySearchPerSettlementCommuneWorker(@RequestParam("name") String name, @PathVariable("settlement.name") String settlement) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/HomePage2LocalniciLocalitate");
        mav.addObject("peoples", peopleService.getSearchResultForSettlement(name, settlement));
        return mav;
    }

    @GetMapping("/homePage2/{people.id}/detalii")
    public ModelAndView displayLocalDetailsWorker(@PathVariable("people.id") String peopleId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/ChangeStateWorker");
        mav.addObject("states", peopleService.getState());
        mav.addObject("people", peopleService.details(peopleId));
        return mav;
    }

    @PostMapping("/homePage2/{people.id}/detalii")
    public ModelAndView displayEditedPeople(@RequestParam("selectState") String state, @RequestParam("observatii") String observatii) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/homePage2");
        peopleService.updatePeople(state, observatii);
        return mav;
    }

    @GetMapping("/addLocalnici")
    public ModelAndView displayAddForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/AddPeople");
        mav.addObject("states", peopleService.getState());
        mav.addObject("settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        return mav;
    }


    @PostMapping("/addLocalnici")
    public ModelAndView insertIntoDb(
            @RequestParam("name") String name,
            @RequestParam("state") String state,
            @RequestParam("observatii") String observatii,
            @RequestParam("date") String date,
            @RequestParam("settlement") String settlement,
            @RequestParam("isIzolat") boolean izolat,
            @RequestParam("map") String map,
            @RequestParam("mijloc de acces") String mijlocDeAcces,
            @RequestParam("isHandicap") boolean handicap,
            @RequestParam("tip handicap") String tipHandicap,
            @RequestParam("picture") String  picture) throws IOException
            {
                ModelAndView mav = new ModelAndView();


        if (!name.equals("") && !date.equals("")) {
            mav.setViewName("redirect:/homePage2");
            List<Settlement> settlements = settlementService.displaySettlements(session.getAttribute("selected_commune").toString());
            PeopleAdapter peopleAdapter = new PeopleAdapter();
            peopleAdapter.setName(name);
            peopleAdapter.setState(state);
            peopleAdapter.setObservatii(observatii);
            peopleAdapter.setDate(date);
            peopleAdapter.setSettlement(settlementService.getSettlement(settlement, settlements));
            peopleAdapter.setPicture(picture);
            if (izolat) {
                peopleAdapter.setMap(map);
                peopleAdapter.setMijlocDeAcces(mijlocDeAcces);
            }
            if (handicap) {
                peopleAdapter.setTipHandicap(tipHandicap);
            }
            peopleAdapterService.PeopleAdapterToPeople(peopleAdapter, izolat, handicap);
        } else {
            String error = "Campuri obligatorii";
            mav.setViewName("/HomePage2/AddPeople");
            mav.addObject("error", error);
            mav.addObject("states", peopleService.getState());
            mav.addObject("settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        }
        return mav;
    }

    @GetMapping("/homePage2/{people.id}/edit")
    public ModelAndView displayEditForm(@PathVariable("people.id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/EditPeople");
        mav.addObject("peopleAdapter", adapterEditService.updatePeople(id));
        mav.addObject("settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        mav.addObject("states", peopleService.getState());
        return mav;
    }

    @PostMapping("/homePage2/{people.id}/edit")
    public ModelAndView editPeople(
            @PathVariable("people.id") int id,
            @RequestParam("name") String name,
            @RequestParam("state") String state,
            @RequestParam("observatii") String observatii,
            @RequestParam("date") String date,
            @RequestParam("settlement") String settlement,
            //@RequestParam("map")String map,
            @RequestParam("mijloc de acces") String mijlocDeAcces,
            @RequestParam("tip handicap") String handicap,
            @RequestParam("isIzolat") boolean isIzolat,
            //@RequestParam("mapEdit") String mapEdit,
            @RequestParam("mijloc de accesEdit") String mijlocDeAccesEdit,
            @RequestParam("isHandicap") boolean isHandicap,
            @RequestParam("tip handicapEdit") String tipHandicapEdit,
            @RequestParam("isNotIzolat") boolean isNotIzolat,
            @RequestParam("isNotHandicap") boolean isNotHandicap
    ) {
        ModelAndView mav = new ModelAndView();
        if (isIzolat && isHandicap && isNotHandicap && isNotIzolat) {
            String error = "Aceasta actiune este imposibila, va rugam revizuiti";
            mav.addObject("error", error);
            mav.setViewName("/HomePage2/EditPeople");
            mav.addObject("peopleAdapter", adapterEditService.updatePeople(id));
            mav.addObject("settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
            mav.addObject("states", peopleService.getState());
        } else {
            PeopleAdapterEdit editedPeople = adapterEditService.editPeople(id, name, state, observatii, date, settlement, mijlocDeAcces, handicap, mijlocDeAccesEdit, tipHandicapEdit, isIzolat, isHandicap);
            adapterEditService.updateDeleteInsert(editedPeople, isHandicap, isIzolat, isNotHandicap, isNotIzolat);
            mav.setViewName("/HomePage2/displayEdit");
            mav.addObject("peopleEdit", editedPeople);
        }
        return mav;
    }

    @GetMapping("/homePage2/{people.id}/delete")
    public ModelAndView deletePeople(@PathVariable("people.id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/homePage2");
        peopleService.deletePeople(id);
        return mav;
    }


}
