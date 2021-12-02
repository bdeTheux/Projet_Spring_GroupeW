package Backend.Backendbasket.service;

import Backend.Backendbasket.model.BackendBasket;
import Backend.Backendbasket.repo.BackendBasketRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackendBasketService {
    private final BackendBasketRepo repo;

    public BackendBasketService (BackendBasketRepo repo){this.repo=repo;}

    public Iterable<BackendBasket> findAll() {

        return repo.findAll();
    }

    public Iterable<BackendBasket> findAllByUserId(int userId){
       return repo.findAllByUserId(userId);
    }

    public BackendBasket saveBasket(BackendBasket basket){
        return repo.save(basket);
    }

    public BackendBasket findById(int id){
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id " + id));
    }

    public void updateQuantity(int id  , int nQuantity) {
        BackendBasket basket = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id " + id) );
        basket.setQuantity(nQuantity);
    }

    public void deleteProduct(int id){
        BackendBasket basket = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id " + id) );
        repo.delete(basket);

    }
    public void payBasket(int userId ){
        Iterable<BackendBasket> basketsPaid =repo.findAllByUserId(userId);
        for (BackendBasket bas: basketsPaid) {
            repo.delete(bas);
        }

    }
}
