package siit.web.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Commune;
import siit.sevices.ComunneService;

@Controller
public class CommuneAdminController {
    @Autowired
    ComunneService comunneService;

    @GetMapping("/comune")
    public ModelAndView displayCommunePage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/Admin/Commune");
        mav.addObject("commune",comunneService.viewModelCommune());
        return mav;
    }

    @PostMapping("/comune")
    public ModelAndView insert(@RequestParam("name") String name){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/comune");
        comunneService.insertNewCommune(name);
        return  mav;
    }
    @GetMapping("comune/{commune.id}/delete")
    public ModelAndView deleteCommune(@PathVariable("commune.id") int id ){
        ModelAndView mav= new ModelAndView();
        mav.setViewName("redirect:/comune");
        comunneService.delete(id);
        return mav;
    }
}
