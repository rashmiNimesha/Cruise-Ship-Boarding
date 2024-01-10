package com.company;

public class Queue {

    private Passenger[] passengers = new Passenger[3];
    private int cabin;
    private boolean free;

    public Queue(int var1, boolean var2, Passenger[] var3) {
        this.cabin = var1;
        this.free = var2;
        this.passengers = var3;
    }

    public int getCabin() {
        return this.cabin;
    }

    public void setCabin(int var1) {
        this.cabin = var1;
    }

    public boolean isFree() {
        return this.free;
    }

    public void setFree(boolean var1) {
        this.free = var1;
    }

    public Passenger[] getPassengers() {
        return this.passengers;
    }

    public void setPassengers(Passenger[] var1) {
        this.passengers = var1;
    }
}
