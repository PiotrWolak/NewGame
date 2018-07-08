package edu.piotr;

public class Tower extends Building {
    private int defense;

    public Tower(){
        setName("Tower");
        defense = 10;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        defense += 10;
    }
}
