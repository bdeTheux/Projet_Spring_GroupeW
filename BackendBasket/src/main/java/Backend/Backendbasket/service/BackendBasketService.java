package Backend.Backendbasket.service;

import Backend.Backendbasket.modele.BackendBasket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackendBasketService {

    private List<BackendBasket> baskets = new ArrayList<>();

    public List<BackendBasket> findAll(){
        return baskets;
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
