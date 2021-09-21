package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CreateAccountController {


    @GetMapping("/createAccount")
    public ModelAndView displayCreateAccountForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("CreateAccount");
        return mav;
    }

}

