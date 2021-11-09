package Backend.Backendbasket.service;

import Backend.Backendbasket.model.BackendBasket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackendBasketService {

    private List<BackendBasket> baskets = new ArrayList<>();

    public List<BackendBasket> findAll(int id_user){
        List<BackendBasket> usersBaskets = new ArrayList<>();
        for (BackendBasket bask : baskets) {
            if (bask.getId_user() == id_user) usersBaskets.add(bask);
        }
        return usersBaskets;
    }

    public BackendBasket findById(int id){
        for(BackendBasket b : baskets) if(b.getId() == id) return b;
        return null;
    }

    public void updateQuantity(int id  , int nquantity) {
            findById(id).setQuantity(nquantity);
    }

    public void deleteProduct(int id){
        baskets.remove(findById(id));
    }
}
