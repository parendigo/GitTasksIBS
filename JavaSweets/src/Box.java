import java.util.*;

public class Box implements Manageable{
    private TreeMap<String, Sweets> sweets = new TreeMap<String, Sweets>();

    private int weight = 0;
    private int price = 0;

    private int getWeight() {
        return weight;
    }

    // Добавляем сладость
    public void addSweet (Sweets sweet) {
        this.sweets.put(sweet.getName(), sweet);
        this.weight += sweet.getWeight();
        this.price += sweet.getPrice();
    }
    // Удаляем через ссылку на объект
    public void removeSweet (Sweets sweet) {
        this.weight -= sweet.getWeight();
        this.price -= sweet.getPrice();
        this.sweets.remove(sweet.getName());
    }
    // Удаляем через имя
    public void removeSweet (String name) {
        this.weight -= this.sweets.get(name).getWeight();
        this.price -= this.sweets.get(name).getPrice();
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

    // Метод для уменьшения веса коробки, который удаляет сладости по очереди с наименьшим весом.
    // Если вес одинаковый, то удаляет сладость с наименьшей ценой.
    public void changeBoxWeight (int weight) {
        TreeMap<String, Sweets> tmpSweets = new TreeMap<String, Sweets>();

        /* Проверяем, есть ли конфеты весом больше, чем заказанный вес коробки */
        for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
            if (entry.getValue().getWeight() > weight)
                removeSweet(entry.getValue().getName());
        }

        /* Убираем из коробки сладости по очереди с наименьшим весом, если вес одинаковый, то с наименьшей ценой */
        while (weight < this.weight) {
            Map.Entry<String, Sweets> ent = sweets.firstEntry();
            int tmpWeight = ent.getValue().getWeight();
            int tmpPrice = ent.getValue().getPrice();
            String name = null;
            for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
                if (entry.getValue().getWeight() <= tmpWeight && entry.getValue().getPrice() <= tmpPrice) {
                    tmpWeight = entry.getValue().getWeight();
                    tmpPrice = entry.getValue().getPrice();
                    name = entry.getKey();
                }
            }
            tmpSweets.put(name, sweets.get(name));
            removeSweet(name);
        }

        /* Смотрим, можем ли мы добавить обратно конфет, чтобы было ближе к заданному значению веса */
        int tmpWeight = 0;
        String name = null;
        for (Map.Entry<String, Sweets> entry : tmpSweets.entrySet()) {
            if (this.weight + entry.getValue().getWeight() <= weight &&
                    this.weight + entry.getValue().getWeight() > tmpWeight) {
                name = entry.getKey();
            }
        }
        if (name != null) {
            addSweet(tmpSweets.get(name));
        }
    }

    // Метод для уменьшения цены коробки, который удаляет сладости по очереди с наименьшей ценой.
    // Если цены одинакова, то удаляет сладость с наименьшим весом
    public void changeBoxPrice (int price) {
        TreeMap<String, Sweets> tmpSweets = new TreeMap<String, Sweets>();

        /* Убираем конфеты с ценой выше, чем цена заказанной коробки */
        for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
            if (entry.getValue().getPrice() > price)
                removeSweet(entry.getValue().getName());
        }

        /* Убираем конфеты по очереди с наименьшей ценой */
        while (price < this.price) {
            Map.Entry<String, Sweets> ent = sweets.firstEntry();
            int tmpWeight = ent.getValue().getWeight();
            int tmpPrice = ent.getValue().getPrice();
            String name = null;
            for (Map.Entry<String, Sweets> entry : sweets.entrySet()) {
                if (entry.getValue().getPrice() <= tmpPrice) {
                    tmpWeight = entry.getValue().getWeight();
                    tmpPrice = entry.getValue().getPrice();
                    name = entry.getKey();
                }
            }
            tmpSweets.put(name, sweets.get(name));
            removeSweet(name);
        }

        /* Смотрим, можем ли добавить конфеты обратно, чтобы цена была ближе к заявленной */
        int tmpPrice = 0;
        String name = null;
        for (Map.Entry<String, Sweets> entry : tmpSweets.entrySet()) {
            if (this.price + entry.getValue().getPrice() <= price &&
                    this.price + entry.getValue().getPrice() > tmpPrice) {
                name = entry.getKey();
            }
        }
        System.out.println(name);
        if (name != null) {
            addSweet(tmpSweets.get(name));
        }
    }
}
