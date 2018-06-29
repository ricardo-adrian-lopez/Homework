package com.mobileapps.training.testfriday.JungleProblem;

public class Tiger extends Animal {

    private static int amountOfTigers = 0;

    public Tiger(){
        Tiger.amountOfTigers++;
    }

    @Override
    public void sleep() {
        super.increaseEnergy(5);
    }

    public void eatFood(Food food) {
        if (food!=Food.GRAIN){
            super.eatFood();
        }
    }

    public int getAmountOfTigers(){
        return amountOfTigers;
    }

    public void swim(){
        super.decreaseEnergy(6);
    }
}
