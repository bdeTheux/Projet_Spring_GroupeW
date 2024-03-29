package grouped.usersfront.controller;

import grouped.usersfront.model.User;
import grouped.usersfront.model.UserDTO;
import grouped.usersfront.service.UsersFrontService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static grouped.usersfront.service.ServiceConstants.*;

@Controller
public class UsersFrontController {

    private final UsersFrontService service;

    public UsersFrontController(UsersFrontService service) {
        this.service = service;
    }

    @GetMapping
    public String home(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User user = service.getUserFromToken(token);
        if (user != null) {
            service.addConnectionInModel(model, Integer.toString(user.getId()));
        }
        return "index";
    }

    @GetMapping("/all")
    public String all(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User user = service.getUserFromToken(token);
        if (user == null) {
            return ROOT_REDIRECT;
        }
        service.addConnectionInModel(model, Integer.toString(user.getId()));
        model.addAttribute("users", service.findAll());
        return "userList";
    }

    @GetMapping("/login")
    public String login(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User user = service.getUserFromToken(token);
        if (user != null) {
            service.addConnectionInModel(model, Integer.toString(user.getId()));
            return ROOT_REDIRECT;
        }
        service.addUserInModel(model);
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        int userId = service.checkUser(user);
        if (userId == -1) {
            redirectAttributes.addFlashAttribute(USER, user);
            redirectAttributes.addFlashAttribute(MESSAGE, "Données invalides");
            return new ModelAndView("redirect:/login");
        }
        service.insertCookieAfterLogin(response, userId);
        return service.redirectToProductPage();
    }

    @GetMapping("/signin")
    public String signin(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User user = service.getUserFromToken(token);
        if (user != null) {
            service.addConnectionInModel(model, Integer.toString(user.getId()));
            return ROOT_REDIRECT;
        }
        service.addUserInModel(model);
        return "signin";
    }

    @PostMapping("/signin")
    public ModelAndView signin(@ModelAttribute User user, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        /*if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return service.errorOnSignin(user, redirectAttributes, "Les mots de passe ne correspondent pas");
        }*/
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //user.setId(1);
        try {
            service.createUser(user);
        } catch (Exception e) {
           // return service.errorOnSignin(user, redirectAttributes, "L'adresse email est déjà utilisée");
        }
        service.insertCookieAfterLogin(response, user.getId());
        String url = "http://localhost:7000/candies";
        return new ModelAndView(new RedirectView(url));
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(TOKEN, null);
        cookie.setMaxAge(0); // the cookie will be deleted instantly
        response.addCookie(cookie);
        return new ModelAndView(ROOT_REDIRECT);
    }

    @GetMapping("/account")
    public String account(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User user = service.getUserFromToken(token);
        if (user == null) {
            return ROOT_REDIRECT;
        }
        service.addConnectionInModel(model, Integer.toString(user.getId()));
        service.addUserInModel(model, user);
        return "account";
    }

    @PostMapping("/account/password")
    public ModelAndView editAccountPassword(@ModelAttribute UserDTO user, RedirectAttributes redirectAttributes,
                                            @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User u = service.getUserFromToken(token);
        if (u == null)
            return new ModelAndView(ROOT_REDIRECT);
        if (!user.getPassword().equals(user.getPasswordConfirm()))
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_PASSWORD, "Les mots de passe ne correspondent pas");
        if (!service.checkUserPassword(user.getActualPassword(), u.getPassword()))
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_PASSWORD, "Mot de passe invalide");

        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        try {
            service.updateUser(u.getId(), u);
        } catch (Exception e) {
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_PASSWORD, "Un problème est survenu");
        }
        return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE, "Vos données ont été modifiées");
    }

    @PostMapping("/account/data")
    public ModelAndView editAccountData(@ModelAttribute UserDTO user, RedirectAttributes redirectAttributes,
                                        @CookieValue(value = TOKEN, defaultValue = NONE) String token) {
        User u = service.getUserFromToken(token);
        if (u == null)
            return new ModelAndView(ROOT_REDIRECT);
        if (!service.checkUserPassword(user.getPassword(), u.getPassword()))
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_DATA, "Mot de passe invalide");
        if (user.getAddress().isEmpty() || user.getAddress().isBlank())
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_DATA, "L'adresse n'est pas valide'");
        if (user.getEmail().isEmpty() || user.getEmail().isBlank())
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_DATA, "L'email n'est pas valide");
        if (user.getNickname().isEmpty() || user.getNickname().isBlank())
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_DATA, "Le pseudo n'est pas valide");

        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        u.setAddress(user.getAddress());
        u.setEmail(user.getEmail());
        u.setNickname(user.getNickname());

        try {
            service.updateUser(u.getId(), u);
        } catch (Exception e) {
            return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE_DATA, "Un problème est survenu");
        }
        return service.messageOnUpdateAccount(user, redirectAttributes, MESSAGE, "Vos données ont été modifiées");
    }

    @GetMapping("/{id}")
    public ModelAndView deleteUser(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token, @PathVariable("id") int id) {
        User u = service.getUserFromToken(token);
        if (u == null || u.getId() == id)
            return new ModelAndView(ROOT_REDIRECT);
        try {
            service.deleteUser(id);
        } catch (Exception e) {
            model.addAttribute(MESSAGE, "Un problème est survenu");
        }
        return new ModelAndView("redirect:/all");
    }

    @GetMapping("/toggleStatus/{id}")
    public ModelAndView toggleUserStatus(Model model, @CookieValue(value = TOKEN, defaultValue = NONE) String token, @PathVariable("id") int id) {
        User u = service.getUserFromToken(token);
        if (u == null || u.getId() == id)
            return new ModelAndView(ROOT_REDIRECT);
        User user = service.findById(id);

        try {
            service.updateUser(id, user);
        } catch (Exception e) {
            model.addAttribute(MESSAGE, "Un problème est survenu");
        }
        return new ModelAndView("redirect:/all");
    }
}
