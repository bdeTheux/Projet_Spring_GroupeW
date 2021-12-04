package Frontend.FrontendDetailProduct.controller;

import Frontend.FrontendDetailProduct.model.Basket;
import Frontend.FrontendDetailProduct.model.Candy;
import Frontend.FrontendDetailProduct.model.Comment;
import Frontend.FrontendDetailProduct.proxy.BasketProxy;
import Frontend.FrontendDetailProduct.proxy.CandyProxy;
import Frontend.FrontendDetailProduct.proxy.CommentProxy;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/candy")
public class DetailProductController {
    private CandyProxy candyProxy;
    private CommentProxy commentProxy;
    private BasketProxy basketProxy;
    private final JWTVerifier verifier;
    private static Algorithm jwtAlgo;

    public DetailProductController(CandyProxy candyProxy, CommentProxy commentProxy, BasketProxy basketProxy){
        this.candyProxy = candyProxy;
        this.commentProxy = commentProxy;
        this.basketProxy = basketProxy;
        this.jwtAlgo = Algorithm.HMAC256("super_secret");
        this.verifier = JWT.require(jwtAlgo).withIssuer("auth0").build();
    }
    public int verify(String token){
        try{
            DecodedJWT decodedJWT = verifier.verify(token);
            int userId = decodedJWT.getClaim("user").asInt();
            return userId;
        }catch (Exception e){
            return -1;
        }
    }

    @GetMapping("/{id}")
    public String candy(@CookieValue(value= "Authorization",defaultValue = "none") String token, @PathVariable("id") int id, Model model){
        Candy candy = candyProxy.findById(id);
        List<Comment> comments = commentProxy.getCommentsByCandy(id);
        int avgRating;
        if(comments.isEmpty()) {
            avgRating = -1;
        }
        else{
            avgRating = (int) commentProxy.averageByCandyId(id);
        }
        model.addAttribute("userId",verify(token));
        model.addAttribute("candy", candy);
        model.addAttribute("comments", comments);
        model.addAttribute("average", avgRating);
        model.addAttribute("comment", new Comment());
        model.addAttribute("basket", new Basket());
        return "candy";
    }

    @PostMapping("/{id}")
    public ModelAndView createComment(@CookieValue(value= "Authorization",defaultValue = "none") String token, @ModelAttribute Comment comment, @PathVariable("id") int candyId) {
        if(token == null || token.equals("none")){
            return new ModelAndView(new RedirectView("http://localhost:7003/signin"));
        }
        System.out.println(comment.toString());
        Comment c = comment;
        c.setCreationDate(LocalDate.now());
        c.setUserId(1);
        c.setCandyId(candyId);
        c.setState(Comment.States.VALIDE.name());
        System.out.println(c.toString());
        commentProxy.addComment(c);
        return new ModelAndView("redirect:/candy/" + c.getCandyId());
    }

    @PostMapping("/addToBasket/{id}")
    public ModelAndView addToBasket(@CookieValue(value= "Authorization",defaultValue = "none") String token, @ModelAttribute Basket basket, @PathVariable("id") int candyId){
        if(token == null || token.equals("none")){
            return new ModelAndView(new RedirectView("http://localhost:7003/signin"));
        }
        System.out.println(basket.toString());
        Basket b = basket;
        b.setUserId(1);
        b.setProductId(candyId);
        b.setQuantity(1);
        System.out.println(b.toString());
        basketProxy.createBasket(token, b);
        return new ModelAndView("redirect:/candy/" + b.getProductId());
    }
}
