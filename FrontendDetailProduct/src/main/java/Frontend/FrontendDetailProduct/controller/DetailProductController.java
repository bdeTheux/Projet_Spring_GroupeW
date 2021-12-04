package Frontend.FrontendDetailProduct.controller;

import Frontend.FrontendDetailProduct.model.Candy;
import Frontend.FrontendDetailProduct.model.Category;
import Frontend.FrontendDetailProduct.model.Comment;
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

    public DetailProductController(CandyProxy candyProxy, CommentProxy commentProxy){
        this.candyProxy = candyProxy;
        this.commentProxy = commentProxy;
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
        return "candy";
    }

    @PostMapping("")
    public ModelAndView createComment(@ModelAttribute Comment comment, @RequestParam(name="rating", required = false) int rating) {
        System.out.println(comment.toString());
        comment.setCreationDate(LocalDate.now());
        comment.setUserId(1);
        comment.setCandyId(1);
        comment.setState(Comment.States.VALIDE.name());
        System.out.println(comment.toString());
        commentProxy.addComment(comment);
        return new ModelAndView("redirect:/candy/" + comment.getCandyId());
    }
}
