package me.seungyeol.StrategyPattern;

public class RubberDuck extends Duck{

    public RubberDuck(FlyBehavior f, QuackBehavior q) {
        flyBehavior = f;
        quackBehavior = q;
    }
    @Override
    public void display() {
        System.out.println("HIHI");
    }

    public static void main(String[] args) {
        Duck duck = new RubberDuck(new FlyNoWay(),new MuteQuack());
        duck.performFly();
        duck.performQuack();
    }

}
