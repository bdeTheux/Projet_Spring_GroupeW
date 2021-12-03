package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.Basket;
import Frontend.FrontendBasket.proxies.FrontendBasketProxy;
import Frontend.FrontendBasket.proxies.ProductProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import Frontend.FrontendBasket.model.Candy;
@Controller
@RequestMapping("/basket")
public class FrontendBasketController {
    private FrontendBasketProxy basketProxy;
    private ProductProxy productProxy;
 //   private UserProxy userProxy;

    public FrontendBasketController(FrontendBasketProxy basketProxy,ProductProxy productProxy){//UserProxy userProxy) {
        this.basketProxy = basketProxy;
        this.productProxy=productProxy;
       // this.userProxy=userProxy;
    }

    @GetMapping("")
    public String displayList(@RequestParam()int id_user,Model model){
        Iterable<Basket> baskets = (Iterable<Basket>) basketProxy.findAllByUserId(id_user);
        model.addAttribute("baskets", baskets);
        return "basket";
    }

    @PostMapping("")
    public ModelAndView createBasket(@ModelAttribute Basket basket) {
        basketProxy.createBasket(basket);
        return new ModelAndView("redirect:/");
    }
    //add delete methode
    @GetMapping
    public Object detailProduct(@RequestParam() int productId,Model model ){
        Candy candy =productProxy.findById(productId);
        model.addAttribute("candy", candy);
        return "basket";
    }
    //add paid method
}
