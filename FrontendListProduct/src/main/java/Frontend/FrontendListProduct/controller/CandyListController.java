package Frontend.FrontendListProduct.controller;


import Frontend.FrontendListProduct.model.Category;
import Frontend.FrontendListProduct.proxy.CandyProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/candies")
public class CandyListController {
    private CandyProxy proxy;

    public CandyListController(CandyProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping
    public String displayList(@RequestParam(required = false)Category category, Model model){
        model.addAttribute("candies", category==null ? proxy.findAll(): proxy.findAllByCategory(category));
        return "candies";
    }

}
