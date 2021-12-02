package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.FrontendBasket;
import Frontend.FrontendBasket.proxies.FrontendBasketProxy;
import Frontend.FrontendBasket.proxies.ProductProxy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class FrontendBasketController {
    private FrontendBasketProxy basketProxy;
    private ProductProxy productProxy;

    public FrontendBasketController(FrontendBasketProxy basketProxy,ProductProxy productProxy) {
        this.basketProxy = basketProxy;
        this.productProxy=productProxy;
    }

    @GetMapping("/Basket")
    public String displayList(@RequestParam(required = false)int id_user,Model model){
        model.addAttribute("basket", basketProxy.findAllById_user(id_user));
        return "basket";
    }

    @PostMapping
    public ModelAndView createBasket(@ModelAttribute FrontendBasket basket) {
        basketProxy.addBasket(basket);
        return new ModelAndView("redirect:/");
    }
    @GetMapping
    public Object detailProduct(@RequestParam() int id_product,Model model ){
        model.addAttribute("candy", productProxy.findById(id_product));
        return "Basket";
    }
}
