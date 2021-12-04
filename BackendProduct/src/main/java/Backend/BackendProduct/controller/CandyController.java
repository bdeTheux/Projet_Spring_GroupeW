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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candies")
public class CandyController {

    private CandyService service;

    public CandyController(CandyService service){
        this.service = service;
    }
    //Create one
    @PostMapping
    public ResponseEntity<Void> createCandy(@RequestBody Candy candy, @RequestHeader(name = "Authorization")String token) {
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
    public Candy findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    //Delete one
    @DeleteMapping("/delete/{id}")
    public void deleteCandy(@PathVariable("id") int id, @RequestHeader(name = "Authorization")String token) {
        service.deleteCandy(id);
        //return new ModelAndView("redirect:/");
    }
    //Update one
    @PutMapping("/{id}")
    public void updateCandy(@PathVariable("id") int id, @RequestBody Candy candy, @RequestHeader(name = "Authorization")String token) {
        service.updateCandy(candy, id);
    }

    @GetMapping
    public List<Candy> getCandies(@RequestParam(required=false)Category category,
                                  @RequestParam(required = false)String order,
                                  @RequestParam(required=false)Double min,
                                  @RequestParam(required=false)Double max){

        List<Candy> candies = (List<Candy>) service.findAll();
        List<Candy>tmpCandies = candies;
        if(category != null){

            tmpCandies = candies.stream().filter(candy -> candy.getCategory().equals(category)).collect(Collectors.toList());

            candies = tmpCandies;
        }
        if(min != null && max != null){
            tmpCandies = candies.stream().filter(candy -> candy.getPrice() >= min && candy.getPrice() <= max).collect(Collectors.toList());
            candies = tmpCandies;
        }
        if(order != null){
            tmpCandies = candies;
            if(order.equals("asc")){
                tmpCandies = candies.stream().sorted(Comparator.comparing(Candy::getPrice)).collect(Collectors.toList());
            }else if(order.equals("desc")){
                tmpCandies = candies.stream().sorted(Comparator.comparing(Candy::getPrice).reversed()).collect(Collectors.toList());
            }
            candies = tmpCandies;
        }

        return candies;




        /*if(category != null){
            return (List<Candy>) service.findByCategory(Category.valueOf(category));
        }else if(min != null && max != null){
            return (List<Candy>) service.findByPrice(min, max);
        }else{
            return (List<Candy>) service.findAll();
        }*/

    }



}
