package AdventureGame;

public class Obstacle {
    private int id;
    private String name;
    private int damage;
    private int health;
    private int award; //ödül
    private int orijinalHealth;


    public Obstacle(int id, String name , int damage, int health,int award) {
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.orijinalHealth=health;
        this.name = name;
        this.award=award;
    }

    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if(health<0)
            health=0;
        this.health = health;
    }
}
