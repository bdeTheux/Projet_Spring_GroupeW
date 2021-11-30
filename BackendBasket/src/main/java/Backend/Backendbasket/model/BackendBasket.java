package Backend.Backendbasket.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name="basket")

public class BackendBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id , id_user,id_product, quantity;

}
