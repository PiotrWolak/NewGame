package edu.piotr;

import java.util.Scanner;

public class Village {

    private String name;
    private int gold;
    private Farm farm;
    private Tower tower;

    public Village(String name) {
        this.name = name;
        gold = 100;
        farm = new Farm();
        tower = new Tower();
        MakeMoney moneyMaker = new MakeMoney(this);
        moneyMaker.start();
    }

    public boolean upgrade(Building facility) {

        int cost = 10 * facility.getLevel();
        System.out.println("The  cost of upgrading " + facility.getName() + " is " + cost + "gold");
        System.out.println("Do you want to proceed ? 1- yes, 2-not");
        int choice = Main.getChoiceMenu();
        switch (choice) {
            case 1:
                if (this.getGold() >= cost) {
                    this.setGold(this.getGold() - cost);
                    facility.upgrade();
                } else {
                    System.out.println("Not enough gold");
                    return false;
                }
                break;
            case 2:
                return false;
            default:
                return false;
        }
        return false;
    }

    public class MakeMoney extends Thread {

        Village village;

        @Override
        public void run() {
            int goldPerSec = village.getFarm().getIncome();
            while (true) {
                village.setGold(village.getGold() + goldPerSec);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        }

        MakeMoney(Village village) {
            this.village = village;
        }

    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public String getName() {
        return name;
    }

    public Farm getFarm() {
        return farm;
    }

    public Tower getTower() {
        return tower;
    }
}
