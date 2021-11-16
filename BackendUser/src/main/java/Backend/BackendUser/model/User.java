package Backend.BackendUser.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastname;
    private String firstname;
    private String email;

    public User(int id, String lastname, String firstname, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }
    public User() {
    }
}
