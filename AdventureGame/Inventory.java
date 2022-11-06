package AdventureGame;

import java.util.ArrayList;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private ArrayList<String> awards = new ArrayList<>();

    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0, 0);
        this.armor = new Armor(-1, "Kıyafet", 0, 0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    public ArrayList<String> getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards.add(awards);
    }
    public int getAwardsCount(){
        return this.awards.size();
    }
    public boolean SearchAward(String award){
        for(String aw:awards){
            if(award==aw)
                return true;
        }
        return false;
    }
}
