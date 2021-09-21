package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.model.User;
import siit.sevices.ComunneService;
import siit.sevices.SettlementService;
import siit.sevices.Twilio.SmsRequest;
import siit.sevices.User.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ComunneService   comunneService;
    @Autowired
    SettlementService settlementService;
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.GET)
//    protected String displayLoginForm() {
//        return "login";
//    }
    protected ModelAndView displayLoginForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("commune",comunneService.viewModelCommune());
        return mav;
    }




    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView performLogin(HttpSession session,
                                        @RequestParam String user, @RequestParam String password,
                                        @RequestParam String commune) {
        ModelAndView mav = new ModelAndView();
        User userLogged = userService.getLoggedUser(user);

        if (userLogged.getPassword().equals(password)){
            SmsRequest.setUser(userLogged);
            session.setAttribute("logged_user", user);
            session.setAttribute("selected_commune",commune);
            if(userLogged.getRole().equals("pompier")) {
                mav.setViewName("redirect:/homePage1");
            }else if(userLogged.getRole().equals("primarie")){
                mav.setViewName("redirect:/homePage2");
            }else if(userLogged.getRole().equals("admin")){
                mav.setViewName("redirect:/adminPage");
            }
        }
        else {
            String error = "User and password do not match! ";
            mav.setViewName("redirect:/login");
            mav.addObject("error", error);
        }

        return mav;
    }
}
