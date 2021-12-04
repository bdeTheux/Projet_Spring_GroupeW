package Frontend.FrontendDetailProduct.controller;

import Frontend.FrontendDetailProduct.model.Basket;
import Frontend.FrontendDetailProduct.model.Candy;
import Frontend.FrontendDetailProduct.model.Category;
import Frontend.FrontendDetailProduct.model.Comment;
import Frontend.FrontendDetailProduct.proxy.BasketProxy;
import Frontend.FrontendDetailProduct.proxy.CandyProxy;
import Frontend.FrontendDetailProduct.proxy.CommentProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/candy")
public class DetailProductController {
    private CandyProxy candyProxy;
    private CommentProxy commentProxy;
    private BasketProxy basketProxy;

    public DetailProductController(CandyProxy candyProxy, CommentProxy commentProxy, BasketProxy basketProxy){
        this.candyProxy = candyProxy;
        this.commentProxy = commentProxy;
        this.basketProxy = basketProxy;
    }

    @GetMapping("/{id}")
    public String candy(@PathVariable("id") int id, Model model){
        Candy candy = candyProxy.findById(id);
        List<Comment> comments = commentProxy.getCommentsByCandy(id);
        int avgRating;
        if(comments.isEmpty()) {
            avgRating = -1;
        }
        else{
            avgRating = (int) commentProxy.averageByCandyId(id);
        }

        model.addAttribute("candy", candy);
        model.addAttribute("comments", comments);
        model.addAttribute("average", avgRating);
        model.addAttribute("comment", new Comment());
        model.addAttribute("basket", new Basket());
        return "candy";
    }

    @PostMapping("/{id}")
    public ModelAndView createComment(@ModelAttribute Comment comment, @PathVariable("id") int candyId) {
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
    public ModelAndView addToBasket(@ModelAttribute Basket basket, @PathVariable("id") int candyId){
        System.out.println(basket.toString());
        Basket b = basket;
        b.setUserId(1);
        b.setProductId(candyId);
        b.setQuantity(1);
        System.out.println(b.toString());
        basketProxy.createBasket(b);
        return new ModelAndView("redirect:/candy/" + b.getProductId());
    }
}
