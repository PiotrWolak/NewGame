package edu.piotr;

public class Farm extends Building {
    private int income;

    public int getIncome() {
        return income;
    }

    public Farm() {
        setName("Farm");
        income = 1;
    }


    protected void upgrade() {
        this.setLevel(this.getLevel() + 1);
        income = income * 2;
    }
}
