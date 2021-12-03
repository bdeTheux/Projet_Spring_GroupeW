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
        Iterable<Basket> baskets =  basketProxy.findAllByUserId(userId);
        ArrayList<BasketDTO> basketDTOs = new ArrayList<BasketDTO>();
        double totalPrice = 0;

        for (Basket bas :baskets) {
            Candy candy = productProxy.findById(bas.getProductId());
            basketDTOs.add(new BasketDTO(bas.getId(),bas.getQuantity(),bas.getUserId(),bas.getProductId(),candy.getName(),candy.getPrice()));
            totalPrice+=(bas.getQuantity()*candy.getPrice());
        }
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("basketDTOs",basketDTOs);
        return "basket";
    }

    @PostMapping("")
    public ModelAndView createBasket(@ModelAttribute Basket basket) {
        basketProxy.createBasket(basket);
        return new ModelAndView("redirect:/");
    }
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
         basketProxy.deleteProduct(id);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/paid")
    public ModelAndView payBasket(@RequestParam() int userId){
        System.out.println("PAR LAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        basketProxy.payBasket(userId);
        return new ModelAndView("redirect:/paid");
    }
    @GetMapping("/{userId}")
    public String detailsUser(@PathVariable("userId") int userId,Model model ){
        User candy =userProxy.getUser(userId);
        model.addAttribute("user", candy);
        return "paid";
    }

}
