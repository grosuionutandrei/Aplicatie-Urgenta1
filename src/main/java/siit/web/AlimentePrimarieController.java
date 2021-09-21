package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Alimente.TipAliment;
import siit.sevices.Alimente.AlimenteService;

@Controller
public class AlimentePrimarieController {
    @Autowired
    AlimenteService alimenteService;

    @GetMapping("/alimentePrimarie")
    public ModelAndView displayAliments() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/AlimenteView");
        mav.addObject("aliments", alimenteService.getList());
        return mav;
    }

    @PostMapping("/alimentePrimarie")
    public ModelAndView modifyQuantity(@RequestParam("aliment") int alimentId,@RequestParam("nrBucati") int quantity){
        ModelAndView mav= new ModelAndView();
        mav.setViewName("redirect:/alimentePrimarie");
        alimenteService.updateAliment(alimentId,quantity);
        return mav;

    }

    @GetMapping("/alimente/{aliment.id}/delete")
    public ModelAndView deleteAliment(@PathVariable("aliment.id") int alimentId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/alimentePrimarie");
        alimenteService.deleteAliment(alimentId);
        return  mav;

    }

    @GetMapping("/alimentePrimarie/adauga")
    public ModelAndView displayAddform(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/AlimenteAddForm");
        return mav;
    }
    @PostMapping("/alimentePrimarie/adauga")
    public ModelAndView addNewAliment(@RequestParam("tipAliment") String tipAliment,@RequestParam("calories")int calories,@RequestParam("quantity") int quantity){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/alimentePrimarie");
        TipAliment tipAliment1 = new TipAliment(tipAliment,calories,quantity);
        System.out.println(tipAliment1);
        alimenteService.addNewAliment(tipAliment1);
        return mav;
    }

    @GetMapping("/alimentePrimarie/trimite")
    public ModelAndView displaySendAlimenteForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/HomePage2/SendAliments");
        mav.addObject("aliments", alimenteService.getList());
        return mav;
    }
    @PostMapping("/alimentePrimarie/trimite")
        public ModelAndView insertIntoDb(){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/alimentePrimarie");
            return mav;
        }

}
