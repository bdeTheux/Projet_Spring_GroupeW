package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.Basket;
import Frontend.FrontendBasket.model.User;
import Frontend.FrontendBasket.proxies.FrontendBasketProxy;
import Frontend.FrontendBasket.proxies.ProductProxy;
import Frontend.FrontendBasket.proxies.UserProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import Frontend.FrontendBasket.model.Candy;

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

    @GetMapping("")
    public String displayList(@RequestParam()int userId,Model model){
        ArrayList<Basket> baskets = (ArrayList<Basket>) basketProxy.findAllByUserId(userId);
        model.addAttribute("baskets", baskets);
        return "basket";
    }

    @PostMapping("")
    public ModelAndView createBasket(@ModelAttribute Basket basket) {
        basketProxy.createBasket(basket);
        return new ModelAndView("redirect:/");
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        return basketProxy.deleteProduct(id);
    }
    @GetMapping
    public Object detailsProduct(@RequestParam() int productId, Model model ){
        Candy candy =productProxy.findById(productId);
        model.addAttribute("candy", candy);
        return "basket";
    }
    @GetMapping("paid")
    public ModelAndView payBasket(@RequestParam() int userId){
        basketProxy.payBasket(userId);
        return new ModelAndView("redirect:/paid");
    }
    @GetMapping
    public Object detailsUser(@RequestParam() int userId,Model model ){
        User candy =userProxy.getUser(userId);
        model.addAttribute("user", candy);
        return "paid";
    }

}
