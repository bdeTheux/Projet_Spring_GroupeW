package Backend.BackendUser.controller;

import Backend.BackendUser.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserProxy proxy;

    public UserController(UserProxy proxy){
        this.proxy = proxy;
    }

    //for thymeleaf
    @GetMapping
    public String home(Model model) {
        List<User> users = proxy.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User(-1, "", "", ""));
        return "home";
    }
    @GetMapping
    public ModelAndView readOne(@ModelAttribute User user) {
        proxy.saveUser(user);
        return new ModelAndView("redirect:/");
    }


    public List<User> getAllUsers(){
        
    }

}
