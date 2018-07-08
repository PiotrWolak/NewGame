package edu.piotr;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Enter the name of your village");
        Scanner input = new Scanner(System.in);
        String villageName = input.next();
        villageName = villageName.toUpperCase();

        Village village = new Village(villageName);
        int round = 0;
        showMenu();
        while (true) {
            round++;
            if (round % 10 == 0)pickEvent(village,round);
            else menu(village);
        }
    }

    public static int getChoiceMenu() {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (valid != true) {
            try {
                choice = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid argument, please choice one of the above");
            }
        }
        return choice;
    }

    public static void showMenu() {
        System.out.println("1 - upgrade the farm");
        System.out.println("2 - upgrade the tower");
        System.out.println("3 - show treasure");
        System.out.println("4 - show facilities");
        System.out.println("5 - exit");
    }

    public static void menu(Village village) {
        System.out.println("What would you like to do ? Press 0 if you want to see menu");
        int choice = getChoiceMenu();
        switch (choice) {
            case 0:
                showMenu();
                break;
            case 1:
                if (village.upgrade(village.getFarm()))
                    System.out.println("Farm has been upgraded to level" + village.getFarm().getLevel());
                else System.out.println("Farm has not been upgraded");
                break;
            case 2:
                if (village.upgrade(village.getTower()))
                    System.out.println("Tower has been upgraded to level" + village.getTower().getLevel());
                else System.out.println("Tower has not been upgraded");
                break;
            case 3:
                System.out.println("Your treasure is filled up with " + village.getGold() + " gold");
                break;
            case 4:
                System.out.println("Farm on level " + village.getFarm().getLevel());
                System.out.println("Tower on level " + village.getTower().getLevel());
                break;
            case 5:
                exit(0);
                break;
            default:
                showMenu();
        }

    }
    public static void pickEvent(Village village, int i){
        Random generator = new Random();
        boolean random = generator.nextBoolean();
        if(random)event1(village, i);
        else event2(village, i);
    }
    public static void event2(Village village, int i){
        System.out.println("Farm on level " + village.getFarm().getLevel());
        System.out.println("Tower on level " + village.getTower().getLevel());
        System.out.println("An earthquake occured ! Your facilities has been damaged");
        village.getTower().setLevel((int)village.getTower().getLevel()/10);
        village.getFarm().setLevel((int)village.getFarm().getLevel()/10);
        System.out.println("Farm on level " + village.getFarm().getLevel());
        System.out.println("Tower on level " + village.getTower().getLevel());
    }

    public static void event1(Village village, int i) {
        System.out.println("You Village has been attacked by savages, here's what happened");
        if (village.getTower().getLevel() < (int) i / 5) {
            int damage = (i / 2) - village.getTower().getLevel();
            System.out.println("your farm is on " + village.getFarm().getLevel() + " level");
            if(village.getFarm().getLevel()>damage){
                System.out.println("Barbarians has pwned you and decreased your farm level by " + damage);
                village.getFarm().setLevel(village.getFarm().getLevel() - damage);
            }
            else {
                System.out.println("Savages destroyed your farm completly");
                village.getFarm().setLevel(1);
            }
            System.out.println("your farm is on " + village.getFarm().getLevel() + " level");
        } else if (village.getTower().getLevel() >= (int) i / 5) {
            System.out.println("You have defended against Barbarians, well done");
        }
    }
}
