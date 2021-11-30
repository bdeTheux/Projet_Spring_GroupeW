package Backend.BackendUser.repository;

import Backend.BackendUser.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    default Iterable<User> findAll() {
        return null;
    }


}
