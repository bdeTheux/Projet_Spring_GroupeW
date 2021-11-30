package Backend.BackendUser.service;

import Backend.BackendUser.model.User;
import Backend.BackendUser.repository.UserRepository;
import java.lang.Iterable;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private static UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }
    public Iterable<User> findAll() {
        return repo.findAll();
    }
    public User findById(int id){
        return repo.findById(id).orElseThrow(InternalError::new);
    }
    public User saveUser(User user){
        return repo.save(user);
    }
    public void deleteUser(User user){
        repo.delete(user);
    }
    public User updateUser(User user, int id) {
        User u = repo.findById(id).orElseThrow(InternalError::new);
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setEmail(user.getEmail());
        return repo.save(u);
    }
}
