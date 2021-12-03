package Frontend.FrontendBasket.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastname;
    private String firstname;
    private String nickname;
    private String address;
    private LocalDate birthDate;
    private String email;
    private String password;


    public User(int id, String lastname, String firstname, String nickname,String address, LocalDate birthDate, String email, String password) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nickname = nickname;
        this.address =address;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }
    public User() {
    }
}
