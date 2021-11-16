package Backend.BackendProduct.repo;

import Backend.BackendProduct.model.Candy;
import Backend.BackendProduct.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandiesRepository extends CrudRepository<Candy, Integer> {

    @Override
    default Iterable<Candy> findAll() {
        return null;
    }

    Iterable<Candy> findByPrice(double min, double max);

    Iterable<Candy> findByCategory(Category category);
}
