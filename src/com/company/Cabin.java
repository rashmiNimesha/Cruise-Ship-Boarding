package com.company;

public class Cabin {

    private int cabin_no;
    private boolean cabin_status;
    private Passenger[] passengers = new Passenger[3];

    public Cabin(int var1, boolean var2, Passenger[] var3) {
        this.cabin_no = var1;
        this.cabin_status = var2;
        this.passengers = var3;
    }

    public int getCabin_no() {
        return this.cabin_no;
    }

    public void setCabin_no(int var1) {
        this.cabin_no = var1;
    }

    public boolean is_cabin_free() {
        return this.cabin_status;
    }

    public void setCabin_status(boolean var1) {
        this.cabin_status = var1;
    }

    public Passenger[] getPassengers() {
        return this.passengers;
    }

    public void setPassengers(Passenger[] var1) {
        this.passengers = var1;
    }
}
