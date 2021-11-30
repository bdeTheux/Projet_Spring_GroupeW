package Backend.BackendProduct.controller;

import Backend.BackendProduct.model.Candy;
import Backend.BackendProduct.model.Category;
import Backend.BackendProduct.service.CandyService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candies")
public class CandyController {

    private CandyService service;

    public CandyController(CandyService service){
        this.service = service;
    }
    //Create one
    @PostMapping
    public ResponseEntity<Void> createCandy(@RequestBody Candy candy) {
        Candy can = service.saveCandy(candy);
        if(can == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(can.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //Get one by id
    @GetMapping("/{id}")
    public Candy getCandy(@PathVariable("id") int id) {
        return service.findById(id);
    }

    //Delete one
    @DeleteMapping("delete/{id}")
    public ModelAndView deleteCandy(@PathVariable("id") int id) {
        service.deleteCandy(id);
        return new ModelAndView("redirect:/");
    }
    //Update one
    @PutMapping("/{id}")
    public String updateCandy(@PathVariable("id") int id, Model model) {
        model.addAttribute("candy", service.findById(id));
        System.out.println("update get " + service.findById(id));
        return "update";
    }

    @GetMapping
    public List<Candy> getCandies(@RequestParam(required=false)Category category,
                                  @RequestParam(required=false)Double min,
                                  @RequestParam(required=false)Double max){
        if(category != null){
            return (List<Candy>) service.findByCategory(category);
        }else if(min != null && max != null){
            return (List<Candy>) service.findByPrice(min, max);
        }else{
            return (List<Candy>) service.findAll();
        }
    }



}
