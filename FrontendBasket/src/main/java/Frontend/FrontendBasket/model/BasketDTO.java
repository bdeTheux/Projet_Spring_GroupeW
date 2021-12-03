package Frontend.FrontendBasket.model;

public class BasketDTO {
    private int id;
    private int quantity;
    private int userId;
    private int candyId;
    private String candyName;
    private  double candyPrice;
    private double totalPrice;

    public BasketDTO(int id, int quantity, int userId, int candyId, String candyName, double candyPrice) {
        this.id = id;
        this.quantity = quantity;
        this.userId = userId;
        this.candyId = candyId;
        this.candyName = candyName;
        this.candyPrice = candyPrice;
        this.totalPrice = quantity*candyPrice;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUserId() {
        return userId;
    }

    public int getCandyId() {
        return candyId;
    }

    public String getCandyName() {
        return candyName;
    }

    public double getCandyPrice() {
        return candyPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
