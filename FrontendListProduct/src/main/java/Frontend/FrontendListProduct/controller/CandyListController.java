package Frontend.FrontendListProduct.controller;


import Frontend.FrontendListProduct.model.Candy;
import Frontend.FrontendListProduct.model.CandyDTO;
import Frontend.FrontendListProduct.model.Category;
import Frontend.FrontendListProduct.model.PriceRange;
import Frontend.FrontendListProduct.proxy.CandyProxy;
import Frontend.FrontendListProduct.service.CandyService;
import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//@RequestMapping("/candies")
@Controller
public class CandyListController {
    private CandyService service;
    public CandyListController(CandyService service){
        this.service = service;
    }

    @GetMapping("/candies")
    public String candies(@RequestParam(required = false)String category, @RequestParam(required = false)String order, @RequestParam(required = false)Double min, @RequestParam(required = false)Double max,Model model){
        //J'ai mis cette condition car je ne trouve pas comment enlever le 0.0 de base dans le input
        if(min != null && max != null && min == 0.0 && max == 0.0) max = null;
        List<Candy> candiesList = service.findAll(service.findCategory(category), order, min, max);
        List<String> orderList = new ArrayList<String>(Arrays.asList("none", "asc", "desc"));
        System.out.println("order :" + order);
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        model.addAttribute("priceRange", new PriceRange());
        model.addAttribute("orders", orderList);
        model.addAttribute("candies", candiesList);
       // model.addAttribute("candies", category==null ? listCandies(): findByCategory(category));
        model.addAttribute("categories", service.getCategories());
        model.addAttribute("candy", new CandyDTO());
        return "candies";
    }




    @PostMapping("/candies")
    public ModelAndView createCandy(@ModelAttribute CandyDTO candy, @RequestParam(name="cat", required = false) String category) {
        String t = category;
        Candy cand = candy;

        if(category.equals(Category.ELEMENTARY.getName())){
            cand.setCategory(Category.ELEMENTARY);
        }else if(category.equals(Category.MENTAL.getName())){
            cand.setCategory(Category.MENTAL);
        }else if(category.equals(Category.MUTATION.getName())){
            cand.setCategory(Category.MUTATION);
        }else{
            cand.setCategory(Category.PHYSICS);
        }
        if(service.noEmptyField(candy)){
            service.saveCandy(cand);

        }
        return new ModelAndView("redirect:/candies");
    }

    @PostMapping("/candies/update/{id}")
    public ModelAndView updateCandy(@ModelAttribute CandyDTO candy, @RequestParam(name="cat", required = false) String category, @PathVariable("id") int id){
        Candy cdy = candy;
        System.out.println(cdy.toString());
        if(category.equals(Category.ELEMENTARY.getName())){
            cdy.setCategory(Category.ELEMENTARY);
        }else if(category.equals(Category.MENTAL.getName())){
            cdy.setCategory(Category.MENTAL);
        }else if(category.equals(Category.MUTATION.getName())){
            cdy.setCategory(Category.MUTATION);
        }else{
            cdy.setCategory(Category.PHYSICS);
        }
        service.updateCandy(id, cdy);
        return new ModelAndView("redirect:/candies");
    }

    @GetMapping("/candies/update/{id}")
    public String updateCandy(@PathVariable("id")int id, Model model){
        Candy candy = service.findById(id);
        CandyDTO candyDTO = new CandyDTO();

        candyDTO.setId(candy.getId());
        candyDTO.setName(candy.getName());
        candyDTO.setShortDescription(candy.getShortDescription());
        candyDTO.setDetailDescription(candy.getDetailDescription());
        candyDTO.setPrice(candy.getPrice());
        candyDTO.setCategory(candy.getCategory());
        model.addAttribute("candy", candyDTO);
        model.addAttribute("categories", service.getCategories());
        return "updateCandy";
    }


    @PostMapping("/candies/delete/{id}")
    public ModelAndView deleteCandy(@PathVariable("id") int id){
        System.out.println("je suis ici");
        service.deleteCandy(id);
        return new ModelAndView("redirect:/candies");
    }
    @GetMapping("/candy/{id}")
    public ModelAndView getCandy(@PathVariable("id") int id){
        String url = "http://localhost:7001/candy/" + id;
        return new ModelAndView(new RedirectView(url));
    }


}