package com.mobileapps.training.testfriday.JungleProblem;

public abstract class Animal {

    private int energy;

    public int getEnergy(){
        return energy;
    }

    public final void increaseEnergy(int amount){
        this.energy+=amount;
    }

    public final void decreaseEnergy(int amount){
        this.energy-=amount;
    }

    public void makeSound(){
        decreaseEnergy(-3);
    }

    public void eatFood(){
        increaseEnergy(5);
    }

    public void sleep(){
        increaseEnergy(10);
    }

    public int soundOff(){
        makeSound();
        return getEnergy();
    }


}
