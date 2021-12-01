package Frontend.FrontendListProduct.controller;


import Frontend.FrontendListProduct.model.Candy;
import Frontend.FrontendListProduct.model.CandyDTO;
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
    public String candies(@RequestParam(required = false)String category, @RequestParam(required = false)Integer min, @RequestParam(required = false)Integer max,Model model){
        System.out.println("je passe");
        model.addAttribute("candies", category==null ? listCandies(): findByCategory(category));
        //model.addAttribute("candies", listCandies());
        model.addAttribute("categories", getCategories());
        model.addAttribute("candy", new CandyDTO());
        return "candies";
    }

    public List<Candy> findByCategory(String category){
        for(Category cat: Category.values()){
            if(cat.getName().equals(category)){
                return proxy.findAllByCategory(cat);
            }
        }
        return null;
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
    public ModelAndView createCandy(@ModelAttribute CandyDTO candy, @RequestParam(name="cat", required = false) String category) {
        System.out.println(" ici" + candy.toString());
        System.out.println("request = " + category);
        //System.out.println(Category.PHYSICS.getName().equals(category));
        //candy.setCategory(Category.ELEMANTARY);
        //candy.setCategory(Category.ELEMANTARY);
        String t = category;
        System.out.println("celui ci : " +t);
        Candy cand = candy;
        System.out.println("hello" + Category.MUTATION);
        //TO DO Switch case
        if(category.equals(Category.ELEMANTARY.getName())){
            cand.setCategory(Category.ELEMANTARY);
        }else if(category.equals(Category.MENTAL.getName())){
            cand.setCategory(Category.MENTAL);
        }else if(category.equals(Category.MUTATION.getName())){
            cand.setCategory(Category.MUTATION);
        }else{
            cand.setCategory(Category.PHYSICS);
        }
        System.out.println(" cand" + cand);

        proxy.saveCandy(cand);
        return new ModelAndView("redirect:/candies");
    }


}