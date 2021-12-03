package Backend.Backendbasket.repository;

import Backend.Backendbasket.model.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface BasketRepo extends CrudRepository<Basket,Integer> {


     Iterable<Basket> findAllByUserId(int userId);





}
