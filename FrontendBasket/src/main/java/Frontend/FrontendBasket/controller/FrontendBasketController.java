package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.Basket;
import Frontend.FrontendBasket.model.BasketDTO;
import Frontend.FrontendBasket.model.User;
import Frontend.FrontendBasket.proxies.FrontendBasketProxy;
import Frontend.FrontendBasket.proxies.ProductProxy;
import Frontend.FrontendBasket.proxies.UserProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import Frontend.FrontendBasket.model.Candy;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;


@Controller
@RequestMapping("/basket")
public class FrontendBasketController {
    private FrontendBasketProxy basketProxy;
    private ProductProxy productProxy;
    private UserProxy userProxy;

    public FrontendBasketController(FrontendBasketProxy basketProxy, ProductProxy productProxy, UserProxy userProxy) {
        this.basketProxy = basketProxy;
        this.productProxy=productProxy;
        this.userProxy=userProxy;
    }

    @GetMapping()
    public String displayBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token, @RequestParam()int userId, Model model){
        Iterable<Basket> baskets =  basketProxy.findAllByUserId(token,userId);
        ArrayList<BasketDTO> basketDTOs = new ArrayList<BasketDTO>();
        double totalPrice = 0;

        for (Basket bas :baskets) {

            Candy candy = productProxy.findById(bas.getProductId());
            basketDTOs.add(new BasketDTO(bas.getId(),bas.getQuantity(),bas.getUserId(),bas.getProductId(),candy.getName(),candy.getPrice()));
            totalPrice+=(bas.getQuantity()*candy.getPrice());
        }

        model.addAttribute("uBasket",new Basket());
        model.addAttribute("userId",userId);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("basketDTOs",basketDTOs);
        return "basket";
    }

    @PostMapping("")
    public ModelAndView createBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token,@ModelAttribute Basket basket) {
        basketProxy.createBasket(token,basket);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/paid/{userId}")
    public ModelAndView payBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token,@PathVariable("userId") int userId,Model model){
        basketProxy.payBasket(token,userId);
        User user =userProxy.getUser(userId);
        model.addAttribute("address", user.getAddress());
        return new ModelAndView("paid");
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateQuantity(@CookieValue(value= "Authorization",defaultValue = "none") String token,@PathVariable("id") int id, @ModelAttribute Basket bas){

        basketProxy.updateQuantity(token,id, bas);

        String url = "http://localhost:7002/basket?userId="+bas.getUserId();
        return new ModelAndView(new RedirectView(url));
    }


    //redirection navBar and other
    @GetMapping("/candies")
    public ModelAndView getCandies(){
        String url = "http://localhost:7001/candies";
        return new ModelAndView(new RedirectView(url));
    }
    @GetMapping("/profil/{userId}")
    public ModelAndView getProfil(@PathVariable("userId") int userId){
        String url = "http://localhost:8001/user/{id}(id="+userId+")";
        return new ModelAndView(new RedirectView(url));
    }
    @GetMapping("/basket/{userId}")
    public ModelAndView getBasket(@PathVariable("userId") int userId){
        String url = "http://localhost:7002/basket?userId="+userId;
        return new ModelAndView(new RedirectView(url));
    }
}
