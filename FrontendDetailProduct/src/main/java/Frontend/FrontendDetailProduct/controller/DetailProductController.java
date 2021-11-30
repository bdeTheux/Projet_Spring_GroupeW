package Frontend.FrontendDetailProduct.controller;

import Frontend.FrontendDetailProduct.model.Candy;
import Frontend.FrontendDetailProduct.model.Category;
import Frontend.FrontendDetailProduct.proxy.CandyProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/candy")
public class DetailProductController {
    private CandyProxy proxy;

    public DetailProductController(CandyProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public String candy(@PathVariable("id") int id, Model model){
        Candy candy = proxy.findById(id);
        model.addAttribute("candy", candy);
        return "candy";
    }
    /*
    @GetMapping("/candy")
    public String candy(@RequestParam(required = false) Category category, @RequestParam(required = false)Integer min, @RequestParam(required = false)Integer max, Model model){
        model.addAttribute("candies", listCandies());
        model.addAttribute("categories", getCategories());
        return "candies";
    }
    public List<Category> getCategories(){
        List<Category> cat = new ArrayList<>();
        for(Category c : Category.values()){
            cat.add(c);
        }
        return cat;
    }
    public List<Candy> listCandies(){
        return proxy.findAll(null, null, null);
    }
    @PostMapping
    public ModelAndView createCandy(@ModelAttribute Candy candy) {
        proxy.saveCandy(candy);
        return new ModelAndView("redirect:/");
    }
     */
}
