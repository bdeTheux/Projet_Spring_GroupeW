package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.FrontendBasket;
import Frontend.FrontendBasket.proxy.FrontendBasketProxy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class FrontendBasketController {
    private FrontendBasketProxy proxy;

    public FrontendBasketController(FrontendBasketProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/Basket")
    public String displayList(@RequestParam(required = false)int id_user,Model model){
        model.addAttribute("basket", proxy.findAllById_user(id_user));
        return "basket";
    }

    @PostMapping
    public ModelAndView createBasket(@ModelAttribute FrontendBasket basket) {
        proxy.addBasket(basket);
        return new ModelAndView("redirect:/");
    }
    @GetMapping
    public Object detailProduct(@RequestParam() int id_product,Model model ){
        model.addAttribute("candy",proxy.findProductDetails(id_product));
        return "candy";
    }
}
