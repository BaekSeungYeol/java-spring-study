package me.seungyeol.FactoryMethod.prac;

public class vulnerable {
    public static void main(String[] args) {

        Pizza pizza = OrderPizza(new CheesePizza());
    }
    public static Pizza OrderPizza(Pizza pizza) {
        Pizza myPizza = pizza;
        return myPizza;
    }
}
