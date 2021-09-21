package siit.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/logout")
public class LogoutController {
    @RequestMapping(method = RequestMethod.GET )
    protected ModelAndView performLogout(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if(session != null){
            session.removeAttribute("logged_user");
            session.removeAttribute("selected_commune");
            session.invalidate();
            mav.setViewName("redirect:/login");
        }
        return  mav;
    }
}
