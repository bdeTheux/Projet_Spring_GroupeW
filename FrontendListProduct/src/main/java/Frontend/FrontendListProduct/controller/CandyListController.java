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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//@RequestMapping("/candies")
@Controller
public class CandyListController {
    private CandyProxy proxy;
    public CandyListController(CandyProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/candies")
    public String candies(@RequestParam(required = false)String category, @RequestParam(required = false)String order, @RequestParam(required = false)Integer min, @RequestParam(required = false)Integer max,Model model){

        List<Candy> candiesList = proxy.findAll(findCategory(category), order, min, max);
        List<String> orderList = new ArrayList<String>(Arrays.asList("none", "asc", "desc"));
        System.out.println("order :" + order);

        model.addAttribute("orders", orderList);
        model.addAttribute("candies", candiesList);
       // model.addAttribute("candies", category==null ? listCandies(): findByCategory(category));
        model.addAttribute("categories", getCategories());
        model.addAttribute("candy", new CandyDTO());
        return "candies";
    }

    public Category findCategory(String category){
        for(Category cat: Category.values()){
            if(cat.getName().equals(category)){
                return cat;
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
        return proxy.findAll(null, null, null, null);
    }

    @PostMapping("/candies")
    public ModelAndView createCandy(@ModelAttribute CandyDTO candy, @RequestParam(name="cat", required = false) String category) {
        String t = category;
        Candy cand = candy;

        if(category.equals(Category.ELEMANTARY.getName())){
            cand.setCategory(Category.ELEMANTARY);
        }else if(category.equals(Category.MENTAL.getName())){
            cand.setCategory(Category.MENTAL);
        }else if(category.equals(Category.MUTATION.getName())){
            cand.setCategory(Category.MUTATION);
        }else{
            cand.setCategory(Category.PHYSICS);
        }

        proxy.saveCandy(cand);
        return new ModelAndView("redirect:/candies");
    }

    @PostMapping("/candies/delete/{id}")
    public ModelAndView deleteCandy(@PathVariable("id") int id){
        System.out.println("je suis ici");
        proxy.deleteCandy(id);
        return new ModelAndView("redirect:/candies");
    }


}