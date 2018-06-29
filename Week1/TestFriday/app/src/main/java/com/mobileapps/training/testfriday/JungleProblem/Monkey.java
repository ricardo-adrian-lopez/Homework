package com.mobileapps.training.testfriday.JungleProblem;

public class Monkey extends Animal {

    private static int amountOfMonkeys=0;

    public Monkey(){
        Monkey.amountOfMonkeys++;
    }

    @Override
    public void eatFood() {
        super.increaseEnergy(2);
    }

    @Override
    public void makeSound() {
        super.decreaseEnergy(4);
    }

    public String play(){
        if(super.getEnergy()>=8){
            super.decreaseEnergy(   8);
            return "Oooo Oooo Oooo";
        }
        return "Monkey is too tired";
    }

    public void groom(){
        super.decreaseEnergy(1);
    }
}
