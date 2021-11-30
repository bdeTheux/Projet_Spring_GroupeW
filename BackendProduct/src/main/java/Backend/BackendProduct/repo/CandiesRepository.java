package Backend.BackendProduct.repo;

import Backend.BackendProduct.model.Candy;
import Backend.BackendProduct.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandiesRepository extends CrudRepository<Candy, Integer> {

    //@Override
    Iterable<Candy> findAll();

    Candy save(Candy candy);

    @Query("SELECT candy FROM candies candy WHERE candy.price >= ?1 AND candy.price <= ?2")
    Iterable<Candy> findByPrice(double min, double max);

    Iterable<Candy> findByCategory(Category category);
}
