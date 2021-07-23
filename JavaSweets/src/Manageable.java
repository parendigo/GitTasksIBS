public interface Manageable {
    void addSweet (Sweets sweet);
    void removeSweet (Sweets sweet);
    void removeSweet (String name);
    void printSweets();
    void printBoxWeight();
    void printBoxPrice();
    int findSweet(String name);
}
