package com.company;

public class Passenger {

    private String first_name;
    private String surname;
    private int expenses;

    public Passenger(String var1, String var2, int var3) {
        this.first_name = var1;
        this.surname = var2;
        this.expenses = var3;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String var1) {
        this.first_name = var1;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String var1) {
        this.surname = var1;
    }

    public int getExpenses() {
        return this.expenses;
    }

    public void setExpenses(int var1) {
        this.expenses = var1;
    }
}
