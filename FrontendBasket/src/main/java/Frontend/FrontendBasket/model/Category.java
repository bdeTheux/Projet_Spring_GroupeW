package Frontend.FrontendBasket.model;
// this enum isn't in the candy class because,
// if later we implement choclate (for exemple) they will certainly need this enum.
public enum Category {
    PHYSICS("physic"),ELEMENTARY("elementary"), MENTAL("mental"), MUTATION("mutation");

    private final String name;

    Category(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
