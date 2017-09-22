package dadiatre;

public class DadiATre {

    public static void main(String[] args) {
        Lancio l = new Lancio();
        PlayerGame pg1 = new PlayerGame("Giocatore 1", 1, l);
        pg1.start();
        PlayerGame pg2 = new PlayerGame("Giocatore 2", 2, l);
        pg2.start();
        PlayerGame pg3 = new PlayerGame("Giocatore 3", 3, l);
        pg3.start();
    }
    
}
