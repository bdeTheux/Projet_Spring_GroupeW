package Backend.Backendbasket.service;

import Backend.Backendbasket.model.Basket;
import Backend.Backendbasket.repository.BasketRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BasketService {
    private final BasketRepo repo;

    public BasketService(BasketRepo repo){this.repo=repo;}

    public Iterable<Basket> findAllByUserId(int userId){
       return repo.findAllByUserId(userId);
    }

    public Basket createBasket(Basket basket){
        return repo.save(basket);
    }

    public Basket findById(int id){
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id " + id));
    }

    public void updateQuantity(int id  , Basket nBasket) {
        Basket basket = repo.findById(id).orElseThrow(InternalError::new );
        basket.setQuantity(nBasket.getQuantity());
        repo.save(basket);
    }

    public void deleteProduct(int id){
        Basket basket = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id " + id) );
        repo.delete(basket);

    }
}
