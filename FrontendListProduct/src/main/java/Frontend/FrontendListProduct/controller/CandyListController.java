package Frontend.FrontendListProduct.controller;


import Frontend.FrontendListProduct.model.Candy;
import Frontend.FrontendListProduct.model.Category;
import Frontend.FrontendListProduct.proxy.CandyProxy;
import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


//@RequestMapping("/candies")
@Controller
public class CandyListController {
    private CandyProxy proxy;

    public CandyListController(CandyProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/candies")
    public String candies(@RequestParam(required = false)Category category, @RequestParam(required = false)Integer min, @RequestParam(required = false)Integer max,Model model){
        model.addAttribute("candies", listCandies());//category==null ? proxy.findAll(): proxy.findAllByCategory(category));
        model.addAttribute("candy", new Candy());
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

    @PostMapping("/candies")
    public ModelAndView createCandy(@ModelAttribute Candy candy) {

        proxy.saveCandy(candy);
        return new ModelAndView("redirect:/candies");
    }


}