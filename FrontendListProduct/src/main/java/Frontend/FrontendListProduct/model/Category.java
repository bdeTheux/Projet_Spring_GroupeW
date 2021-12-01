package Frontend.FrontendListProduct.model;
// this enum isn't in the candy class because,
// if later we implement choclate (for exemple) they will certainly need this enum.
public enum Category {
    PHYSICS("physic"),ELEMANTARY("elemantary"), MENTAL("mental"), MUTATION("mutation");

    private final String name;

    Category(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }
}
