package siit.web.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.sevices.User.UserService;

@Controller
public class UsersController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ModelAndView displayUsers(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/Admin/Users");
        mav.addObject("users",userService.allUsers());
        return mav;
    }



    @GetMapping("users/{user.id}/delete")
    public ModelAndView delete(@PathVariable("user.id") int id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/users");
        userService.delete(id);
        return mav;
    }
    @GetMapping("/users/add")
    public ModelAndView displayAddFrom(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/Admin/AddUser");
        mav.addObject("roles",userService.getRol());
        return mav;
    }

    @PostMapping("/users/add")
    public ModelAndView addNewUser(@RequestParam("name") String name,@RequestParam("phoneNumber") String phoneNumber,
                                    @RequestParam("rolUser") String rol,@RequestParam("password") String password ){
      ModelAndView mav = new ModelAndView();
      mav.setViewName("redirect:/users");
      userService.insertNewUser(name,phoneNumber,rol,phoneNumber);
      return mav;
    }
}
