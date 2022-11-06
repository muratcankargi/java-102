package AdventureGame;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\n-------Mağazaya Hoşgeldiniz-------");
        boolean showMenu = true;
        while (showMenu) {

            System.out.println("1-Silahlar\n2-Zırhlar\n3-Çıkış Yap");
            int selectCase = 0;
            do {
                System.out.print("Seçiminiz(1,2): ");
                selectCase = input.nextInt();
            } while (selectCase > Weapon.weapons().length || selectCase < 1);

            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Mağazadan Çıktınız.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("\n-------Silahlar-------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "-" + w.getName() + ", Hasar: " + w.getDamage() + ", Ücret: " + w.getPrice());
        }
        System.out.println("0-Çıkış Yap");

    }

    public void buyWeapon() {
        int selectWeaponId = 0;
        do {

            System.out.print("\nSilah Seçiminiz(1,2,3): ");
            selectWeaponId = input.nextInt();
        } while (selectWeaponId > Weapon.weapons().length || selectWeaponId < 0);

        if (selectWeaponId != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                    getPlayer().printPlayer();
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını aldınız!");
                    //paramızdan silahın ücretini çıkardık
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("Güncel paranız : " + this.getPlayer().getMoney());

                    System.out.println("Önceki silahınız : " + this.getPlayer().getInventory().getWeapon().getName());

                    this.getPlayer().getInventory().setWeapon(selectedWeapon);

                    System.out.println("Yeni silahınız : " + this.getPlayer().getInventory().getWeapon().getName());

                }
            }

        }
    }

    public void buyArmor() {
        int selecArmorId = 0;
        do {
            System.out.print("Silah Seçiminiz(1,2,3): ");
            selecArmorId = input.nextInt();
        } while (selecArmorId > Armor.armors().length || selecArmorId < 0);

        Armor selectedArmor = Armor.getArmorObjById(selecArmorId);
        if (selecArmorId != 0) {
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                    getPlayer().printPlayer();
                } else {
                    System.out.println(selectedArmor.getName() + " zırh aldınız!");
                    //paramızdan silahın ücretini çıkardık
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Güncel paranız : " + this.getPlayer().getMoney());

                    System.out.println("Önceki zırh : " + this.getPlayer().getInventory().getArmor().getName());

                    this.getPlayer().getInventory().setArmor(selectedArmor);

                    System.out.println("Yeni zırh : " + this.getPlayer().getInventory().getArmor().getName());

                }
            }

        }
    }

    public void printArmor() {
        System.out.println();
        System.out.println("-------Zırhlar-------");

        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "-" + a.getName() + ", Zırh: " + a.getBlock() + ", Ücret : " + a.getPrice());
        }
        System.out.println("0-Çıkış Yap");

    }
}
