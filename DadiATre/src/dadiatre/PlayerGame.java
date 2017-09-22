package dadiatre;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerGame extends Thread {
    PlayerFrame frame;
    String nome;
    Lancio lancio;
    int id;
    
    public PlayerGame(String nome, int id, Lancio l)
    {
        this.nome = nome;
        this.lancio = l;
        this.frame = new PlayerFrame(l, id);
        this.id = id;
        frame.setTitle(nome);
        frame.setVisible(true);
        frame.updateScores();
    }
    
    @Override
    public void run() {
        while(true)
        {
            try {
                synchronized(lancio) {
                    lancio.wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.updateScores();
        }
    }
}
