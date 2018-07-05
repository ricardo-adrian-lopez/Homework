package com.mobileapps.training.daily2;

public class Room {
    public final boolean isInfected;
    public boolean visited = false;

    Room(boolean infected) {
        isInfected = infected;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return ""+isInfected();
    }
}
