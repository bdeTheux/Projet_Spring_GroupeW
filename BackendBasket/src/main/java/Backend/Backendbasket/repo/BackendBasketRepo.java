package Backend.Backendbasket.repo;

import Backend.Backendbasket.model.BackendBasket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BackendBasketRepo extends CrudRepository<BackendBasket,Integer> {


     Iterable<BackendBasket> findAllByUserId(int userId);


}
