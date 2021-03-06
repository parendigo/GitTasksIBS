public class Main {
    public static void main (String[] args) {

        // Создаем коробку с 6-ю сладостями
        Box box = new Box();
        box.addSweet(new Banana(1, "yellow", 230, 213));
        box.addSweet(new Banana(2, "green", 200, 245));
        box.addSweet(new Coffee(3, "black100/310", 100, 310));
        box.addSweet(new Coffee(3, "black101/310", 101, 310));
        box.addSweet(new Coffee(3, "black99/310", 99, 310));
        box.addSweet(new Coffee(4, "brown", 150, 212));
        box.addSweet(new Coconut(5, "solid", 250, 730));
        box.addSweet(new Coconut(6, "chipped100/120", 100, 120));
        box.addSweet(new Coconut(6, "chipped100/100", 100, 100));
        box.addSweet(new Coconut(6, "chipped100/200", 100,  200));

        // Печатаем содержимое коробки и окончательные вес и цену
        System.out.println("Печатаем содержимое коробки и окончательные вес и цену\n");
        box.printSweets();
        System.out.println();
        box.printBoxWeight();
        box.printBoxPrice();
        System.out.println();

        // убираем по имени желтый банан и делаем перерасчет
        System.out.println("Убираем по имени желтый банан и делаем перерасчет\n");
        box.removeSweet("yellow");
        box.printBoxWeight();
        box.printBoxPrice();
        System.out.println();

        // добавляем новую сладость с перерасчетом
        System.out.println("Добавляем новую сладость с перерасчетом\n");
        Banana banana = new Banana(7, "yellow replacer", 500, 1000);
        box.addSweet(banana);
        box.findSweet("yellow replacer");
        System.out.println();
        box.printBoxWeight();
        box.printBoxPrice();
        System.out.println();

        // Удаляем по объекту недавно созданную сладость и снова перерасчет
        System.out.println("Удаляем по объекту недавно созданную сладость и снова перерасчет\n");
        box.removeSweet(banana);
        box.printBoxWeight();
        box.printBoxPrice();
        System.out.println();

        // Меняем вес коробки и оптимизируем
        System.out.println("Меняем вес коробки и оптимизируем\n");
        box.changeBoxWeight(1000);
        box.printSweets();
        box.printBoxWeight();
        box.printBoxPrice();
        System.out.println();

        // Меняем цену коробки и оптимизируем
        System.out.println("Меняем цену коробки и оптимизируем\n");
        box.changeBoxPrice(550);
        box.printSweets();
        box.printBoxWeight();
        box.printBoxPrice();
        System.out.println();
    }
}
