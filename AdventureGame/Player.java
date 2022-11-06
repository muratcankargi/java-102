package AdventureGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Inventory {

    private int damage;
    private int health;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;
    private int orijinalHealth;


    public Player(String name) {
        this.name = name;
        this.inventory= new Inventory();
    }

    public void selectChar(String PlayerName) {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("--------------------------------------------------------");
        System.out.println("Karakterini Seç, Maceraya Başla!");
        for (GameChar gameChar : charList) {
            System.out.println("Id: " + gameChar.getId() + "\tKarakter: " +
                    gameChar.getName() + "\tHasar:" +
                    gameChar.getDamage() + "\tSağlık:" +
                    gameChar.getHealth() + "\tPara:" +
                    gameChar.getMoney());
        }
        System.out.println("--------------------------------------------------------");

        System.out.print("Seç : ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;

            default:
                System.out.println("Lütfen Geçerli bir değer giriniz.");
                break;
            //burayı while döngüsüyle doğru girene kadar seçim yaptırabiliriz
        }

    }
    public void printPlayer(){
        System.out.println("Karakter: " + this.getCharName() +
                ", Silah: "+this.getInventory().getWeapon().getName()+
                ", Zırh: "+this.getInventory().getArmor().getName()+
                ", Bloklama: "+getInventory().getArmor().getBlock()+
                ", Hasar: " + this.getTotalDamage() +
                ", Sağlık: " + this.getHealth() +
                ", Para: " + this.getMoney());
    }
    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrijinalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setName(gameChar.getName());

    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public int getTotalDamage(){
        return damage+this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health<0){
            health=0;
        }

        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }

}
