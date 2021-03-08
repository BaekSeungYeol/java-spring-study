package me.seungyeol.FactoryMethod.prac;

public class PizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")) {
            pizza =  new CheesePizza();
        } else {
            pizza = new Pizza(){

            };
        }

        return pizza;
    }
}
