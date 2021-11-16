package Backend.BackendProduct.service;

import Backend.BackendProduct.model.Candy;
import Backend.BackendProduct.model.Category;
import Backend.BackendProduct.repo.CandiesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service

public class CandyService {

    private final CandiesRepository repo;

    public CandyService(CandiesRepository repo){
        this.repo = repo;
    }
    public Iterable<Candy> findAll() {

        return repo.findAll();
    }

    public Candy findById(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No object with id " + id));
    }

    public Iterable<Candy> findByPrice(double min, double max){
        return repo.findByPrice(min, max);
    }
    public Iterable<Candy> findByCategory(Category category) {
        return repo.findByCategory(category);
    }

    public Candy saveCandy(Candy candy) {
        return repo.save(candy);
    }

    public Candy updateCandy(Candy candy, int id) {
        Candy candy1 = repo.findById(id).orElseThrow(InternalError::new);
        candy1.setName(candy.getName());
        candy1.setShortDescription(candy.getShortDescription());
        candy1.setDetailDescription(candy.getDetailDescription());
        candy1.setPrice(candy.getPrice());
        candy1.setCategory(candy.getCategory());
        return repo.save(candy1);
    }

    public void deleteCandy (int id){
        Candy candy = repo.findById(id).orElseThrow(InternalError::new);
        repo.delete(candy);
    }

}
