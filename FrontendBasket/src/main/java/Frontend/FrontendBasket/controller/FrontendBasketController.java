package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.Basket;
import Frontend.FrontendBasket.proxies.FrontendBasketProxy;
import Frontend.FrontendBasket.proxies.ProductProxy;
import Frontend.FrontendBasket.proxies.UserProxy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class FrontendBasketController {
    private FrontendBasketProxy basketProxy;
    private ProductProxy productProxy;
    private UserProxy userProxy;

    public FrontendBasketController(FrontendBasketProxy basketProxy,ProductProxy productProxy,UserProxy userProxy) {
        this.basketProxy = basketProxy;
        this.productProxy=productProxy;
        this.userProxy=userProxy;
    }

    @GetMapping("/Basket")
    public String displayList(@RequestParam()int id_user,Model model){
        model.addAttribute("baskets", basketProxy.findAllById_user(id_user));
        return "baskets";
    }

    @PostMapping
    public ModelAndView createBasket(@ModelAttribute Basket basket) {
        basketProxy.addBasket(basket);
        return new ModelAndView("redirect:/");
    }
    @GetMapping
    public Object detailProduct(@RequestParam() int id_product,Model model ){
        model.addAttribute("candy", productProxy.findById(id_product));
        return "candy";
    }
}
