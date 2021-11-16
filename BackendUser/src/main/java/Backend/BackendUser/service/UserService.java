package Backend.BackendUser.service;

import Backend.BackendUser.model.User;
import Backend.BackendUser.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private static UserRepository repo;
    private static int COUNT = 3;

    /*
    public List<User> findAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<User>> response = template.exchange(
                baseUrl + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){}
        );
        return response.getBody();
    }
    */
    public Iterable<User> getUsers(){
        return repo.findAll();
    }
    public User getUser(int id){
        return repo.findById(id).orElseThrow(InternalError::new);
    }
    public User saveUser(User user){
        return repo.save(user);
    }
    public void deleteUser(User user){
        repo.delete(user);
    }
    public User updateDog(User user, int id) {
        User u = repo.findById(id).orElseThrow(InternalError::new);
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        return repo.save(u);
    }
}
