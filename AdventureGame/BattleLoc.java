package AdventureGame;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = randomObstacleNumber();
        System.out.println("Şu andaki konumunuz: " + this.getName());
        if (this.getName() == "Maden") {
            Random rnd = new Random();
            this.getObstacle().setDamage(rnd.nextInt(3, 7));


        }
        if (this.getPlayer().SearchAward(this.award)) {
            System.out.println("Buraya daha önce girdiniz!");
            return true;
        }
        System.out.println("Dikkat! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor!");
        System.out.print("Savaş veya Kaç (S,K):");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {

            System.out.println(this.getName() + " tüm düşmanları yendiniz!");
            if (this.getName() == "Maden") {
                caveAward();
                return true;
            }
            this.getPlayer().setAwards(this.award);
            System.out.println("Ödül olarak " + this.getAward() + " kazandınız!");
            return true;
        }
        if (this.getPlayer().getHealth() == 0) {
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }

    public void caveAward() {
        Random rnd = new Random();
        int number = rnd.nextInt(100);
        System.out.println("Yüzde oranı:" + number);
        if (number <= 15) {
            System.out.println("silah kazandınız!");

            if (number <= 3) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
            } else if (number <= 7.5) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
            } else if (number > 7.5) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));

            }
        }
        if (number > 15 && number <= 30) {
            System.out.println("Zırh Kazandınız!");
            if (number <= 18) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
            } else if (number <= 22.5) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
            } else if (number > 22.5) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
            }
        }
        if (number > 30 && number <= 55) {
            System.out.println("Para kazandınız!");
            if (number <= 35) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            } else if (number <= 42.5) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else if (number > 42.5) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
        } else {
            System.out.println("Ödül alamadınız.");
        }
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrijinalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("Vur veya Kaç (V,K): ");
                String selectCombat = input.nextLine().toUpperCase();
                Random rnd = new Random();
                int firstFit = rnd.nextInt(100);

                if (selectCombat.equals("V")) {
                    if (firstFit < 50) {
                        System.out.println("Vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println(i + ". " + this.getObstacle().getName() + " size vurdu!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0)
                                obstacleDamage = 0;
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else {
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println(i + ". " + this.getObstacle().getName() + " size vurdu!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0)
                                obstacleDamage = 0;
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                        System.out.println("Vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                    }
                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz!");
                //System.out.println(this.getObstacle().getAward() + " para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else
                return false;
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız:" + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı:" + this.getObstacle().getHealth() + "\n");
    }

    public void playerStats() {
        System.out.println("Oyuncu değerleri");
        System.out.println("Sağlık:" + this.getPlayer().getHealth() +
                ", Silah:" + this.getPlayer().getWeapon().getName() +
                ", Hasar:" + this.getPlayer().getTotalDamage() +
                ", Zırh:" + this.getPlayer().getInventory().getArmor().getName() +
                ", Bloklama:" + this.getPlayer().getInventory().getArmor().getBlock() +
                ", Para:" + this.getPlayer().getMoney());

    }

    public void obstacleStats(int i) {
        System.out.println(i + ". " + this.getObstacle().getName() + " Değerleri");
        System.out.println("Sağlık:" + this.getObstacle().getHealth() +
                ", Hasar:" + this.getObstacle().getDamage() +
                ", Ödül:" + this.getObstacle().getAward());
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        //1,ve max kadar döner maxObs=3 se 1,4=1,2,3 üretir
        return r.nextInt(1, getMaxObstacle() + 1);
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }


}
