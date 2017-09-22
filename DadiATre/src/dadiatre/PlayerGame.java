package dadiatre;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerGame extends Thread {
    private final PlayerFrame frame;
    private final String nome;
    private final Lancio lancio;
    private final int id;
    
    public PlayerGame(String nome, int id, Lancio l)
    {
        this.nome = nome;
        this.lancio = l;
        this.frame = new PlayerFrame(l, id);
        this.id = id;
        frame.setTitle(nome);
        frame.setVisible(true);
        frame.update();
    }
    
    @Override
    public void run() {
        while(lancio.isGameRunning())
        {
            try {
                synchronized(lancio) {
                    lancio.wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.update();
            if(lancio.getPunti(id) >= lancio.PUNTIVITTORIA)
            {
                frame.Victory();
                lancio.signalVictory();
            }
        }
    }
}
