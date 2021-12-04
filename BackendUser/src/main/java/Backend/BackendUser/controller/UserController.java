package Backend.BackendUser.controller;

import Backend.BackendUser.model.User;
import Backend.BackendUser.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        System.out.println("id : " + id);
        return service.findById(id);
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        service.updateUser(user, id);
    }
    @GetMapping
    public Iterable<User> getUsers(){ return service.findAll();}
    //TODO

    @DeleteMapping("/{id}")
    public ModelAndView deleteCandy(@PathVariable("id") int id) {
        service.deleteUser(service.findById(id));
        return new ModelAndView("redirect:/");
    }

    //TODO
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody User user){
        User u = service.saveUser(user);
        if(u == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getId())
                .toUri();
        return ResponseEntity.created(location).build();
        //check JWT d'abord
        //User u = service.saveUser(user);
    }

    //TODO
    public ResponseEntity<Void> login(@RequestBody User user){
        //check JWT d'abord
        return null;
    }

    //TODO
    public ResponseEntity<Void> logout(@RequestBody User user){
        //check JWT d'abord
        return null;
    }





}
