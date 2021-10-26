package Backend.Backendbasket.modele;

public class BackendBasket {
    private int id , id_user,id_product, quantity;

    public BackendBasket(int id, int id_user, int id_product, int quantity) {
        this.id = id;
        this.id_user = id_user;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_product() {
        return id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
