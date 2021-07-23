public abstract class Sweets {
    private int id;
    private String name;
    private int weight;
    private int price;

    public Sweets() {
    }

    public Sweets(int id, String name, int weight, int price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
