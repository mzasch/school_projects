package dadiatre;

import java.util.Random;

public class Lancio {
    private volatile int turno = 1; 
    private int puntiG1, puntiG2, puntiG3;
    private final Random rand;
    public final int PUNTIVITTORIA = 20;
    private boolean running;
    
    public Lancio() {
        running = true;
        rand = new Random();
        puntiG1 = 0;
        puntiG2 = 0;
        puntiG3 = 0;
    }
    
    public synchronized boolean isGameRunning() {
        return running;
    }
    
    public int LancioDado() {
        nextTurn();
        return rand.nextInt(6) + 1;
    }    
            
    public synchronized int getPuntiG1() {
        return puntiG1;
    }

    public synchronized void setPuntiG1(int puntiG1) {
        this.puntiG1 = puntiG1;
    }

    public synchronized int getPuntiG2() {
        return puntiG2;
    }
    
    public synchronized int getPunti(int id) {
        int res = 0;
        switch(id)
        {
            case 1:
                res = getPuntiG1();
                break;
            case 2:
                res = getPuntiG2();
                break;
            case 3:
                res = getPuntiG3();
                break;
        }
        return res;
    }
    
    public synchronized void setPuntiG2(int puntiG2) {
        this.puntiG2 = puntiG2;
    }

    public synchronized int getPuntiG3() {
        return puntiG3;
    }

    public synchronized void setPuntiG3(int puntiG3) {
        this.puntiG3 = puntiG3;
    }

    public synchronized void setPunti(int id, int res) {
        switch(id)
        {
            case 1:
                setPuntiG1(getPuntiG1()+res);
                break;
            case 2:
                setPuntiG2(getPuntiG2()+res);
                break;
            case 3:
                setPuntiG3(getPuntiG3()+res);
                break;
        }
        this.notifyAll();
    }

    public synchronized boolean isMyTurn(int id) {
        return turno == id;
    }

    public synchronized void nextTurn() {
        this.turno++;
        if(turno >= 4)
            turno = 1;
    }

    public void signalVictory() {
        running = false;
    }
}
