import java.util.*;

public class Box implements Manageable{
    private TreeMap<String, Sweets> sweets = new TreeMap<String, Sweets>();
    // Добавляем сладость
    public void addSweet (Sweets sweet) {
        this.sweets.put(sweet.getName(), sweet);
    }
    // Удаляем через ссылку на объект
    public void removeSweet (Sweets sweet) {
        this.sweets.remove(sweet.getName());
    }
    // Удаляем через имя
    public void removeSweet (String name) {
        this.sweets.remove(name);
    }
    // Печатаем все сладости
    public void printSweets() {
        for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
            System.out.printf("ID: %d, NAME: %s, WEIGHT: %d g, PRICE: %d$\n", entry.getValue().getId(), entry.getValue().getName(),
                    entry.getValue().getWeight(), entry.getValue().getPrice());
        }
    }
    // Находим сладость по имени и печатаем
    public int findSweet(String name) {
        for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                System.out.printf("ID: %d, NAME: %s, WEIGHT: %d g, PRICE: %d$\n", entry.getValue().getId(), entry.getValue().getName(),
                        entry.getValue().getWeight(), entry.getValue().getPrice());
                return 1;
            }
        }
        System.out.println("Such sweet was not found");
        return 0;
    }
    // Печатаем вес коробки
    public void printBoxWeight() {
        int weight = 0;
        for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
            weight += entry.getValue().getWeight();
        }
        System.out.printf("Box weight is %d g.\n", weight);
    }
    // Печатаем цену коробки
    public void printBoxPrice() {
        int price = 0;
        for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
            price += entry.getValue().getPrice();
        }
        System.out.printf("Box price is %d$.\n", price);
    }
}
