package AdventureGame;

import java.util.Scanner;

public class Game {
    public Player player;
    public Location location;
    private String playerName;
    private Scanner input = new Scanner(System.in);

    public Game() {

    }

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldin!\n");
        System.out.print("Karakterinin Adını Gir:");
        this.playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + ", Hoşgeldiniz!");
        player.selectChar(getPlayerName());

        Location location = null;
        while (true) {
            player.printPlayer();

            System.out.println();
            System.out.println("-------Bölgeler-------");
            System.out.println();
            System.out.println("1-Güvenli Ev-->Burada düşman yoktur.");
            System.out.println("2-Eşya Dükkanı-->Silah ve zırh satın alabilirsiniz.");
            System.out.println("3-Mağara-->Zombilerin olduğu yer. Ödül: Yemek");
            System.out.println("4-Orman-->Vampirlerin olduğu yer. Ödül: Odun");
            System.out.println("5-Nehir-->Ayıların olduğu yer. Ödül: Su");
            System.out.println("6-Maden-->Yılanların olduğu yer. Ödül: Para,silah,zırh");
            System.out.println("0-Oyunu Kapat");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location=new Coal(player);

                    break;
                case 0:
                    location = null;
                    break;
                default:
                    location = new SafeHouse(player);
                    break;
            }

            if (location == null) {
                break;
            }
            if (!location.onLocation()) {
                System.out.println("Game Over!");
                break;
            }
            if (player.getAwardsCount() == 3) {
                System.out.println("YOU WİN!");
                break;
            }


        }
    }

    public String getPlayerName() {
        return playerName;
    }
    //bir yerden değiştirilmesini istemediğim için setPlayerName metotu oluşturmadım.
}
