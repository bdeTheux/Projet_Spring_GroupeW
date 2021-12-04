package Frontend.FrontendBasket.controller;

import Frontend.FrontendBasket.model.Basket;
import Frontend.FrontendBasket.model.BasketDTO;
import Frontend.FrontendBasket.model.User;
import Frontend.FrontendBasket.proxies.FrontendBasketProxy;
import Frontend.FrontendBasket.proxies.ProductProxy;
import Frontend.FrontendBasket.proxies.UserProxy;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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
    private final JWTVerifier verifier;
    private static Algorithm jwtAlgo;

    public FrontendBasketController(FrontendBasketProxy basketProxy, ProductProxy productProxy, UserProxy userProxy) {
        this.basketProxy = basketProxy;
        this.productProxy=productProxy;
        this.userProxy=userProxy;
        this.jwtAlgo = Algorithm.HMAC256("super_secret");
        this.verifier = JWT.require(jwtAlgo).withIssuer("auth0").build();
    }
    public int verifyToken(String token){
        try{
            DecodedJWT decodedJWT = verifier.verify(token);
            int userId = decodedJWT.getClaim("user").asInt();
            return userId;
        }catch (Exception e){
            return -1;
        }
    }

    @GetMapping()
    public ModelAndView displayBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token, @RequestParam()int userId, Model model){
        if(token == null || token.equals("none")){
            return new ModelAndView(new RedirectView("http://localhost:7003/signin"));
        }

        Iterable<Basket> baskets =  basketProxy.findAllByUserId(token,userId);
        ArrayList<BasketDTO> basketDTOs = new ArrayList<BasketDTO>();
        double totalPrice = 0;

        for (Basket bas :baskets) {
            Candy candy = productProxy.findById(bas.getProductId());
            basketDTOs.add(new BasketDTO(bas.getId(),bas.getQuantity(),bas.getUserId(),bas.getProductId(),candy.getName(),candy.getPrice()));
            totalPrice+=(bas.getQuantity()*candy.getPrice());
        }

        model.addAttribute("uBasket",new Basket());
        model.addAttribute("userId",verifyToken(token));
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("basketDTOs",basketDTOs);

        return new ModelAndView("basket");
    }

    @PostMapping("")
    public ModelAndView createBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token,@ModelAttribute Basket basket) {
        if(token == null || token.equals("none")){
            return new ModelAndView(new RedirectView("http://localhost:7003/signin"));
        }

        basketProxy.createBasket(token,basket);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/paid/{userId}")
    public ModelAndView payBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token,@PathVariable("userId") int userId,Model model){
        if(token == null || token.equals("none")){
            return new ModelAndView(new RedirectView("http://localhost:7003/signin"));
        }

        basketProxy.payBasket(token,userId);
        User user =userProxy.getUser(userId);
        model.addAttribute("address", user.getAddress());
        return new ModelAndView("paid");
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateQuantity(@CookieValue(value= "Authorization",defaultValue = "none") String token,@PathVariable("id") int id, @ModelAttribute Basket bas){
        if(token == null || token.equals("none")){
            return new ModelAndView(new RedirectView("http://localhost:7003/signin"));
        }
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
    @GetMapping("/profil")
    public ModelAndView getProfil(@CookieValue(value= "Authorization",defaultValue = "none") String token){
        int userId = verifyToken(token);
        String url = "http://localhost:8001/user/{id}(id="+userId+")";
        return new ModelAndView(new RedirectView(url));
    }
    @GetMapping("/basket")
    public ModelAndView getBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token){
        String url = "http://localhost:7002/basket?userId="+verifyToken(token);
        return new ModelAndView(new RedirectView(url));
    }
}
