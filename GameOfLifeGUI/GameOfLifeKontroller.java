package GameOfLifeGUI;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLifeKontroller {
    Verden verden;
    GameOfLifeView view;
    Timer timer;

    public GameOfLifeKontroller(Verden verden) {
        this.verden = verden;
    }
    
    public void start() {
        view = new GameOfLifeView(verden, this);
    }

    public void startSimulering() {
        //stopper timer hvis den er allerede er i gang
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verden.oppdatering();
                view.oppdaterGrid();
            }
        });

        timer.start();

    }
    
    public void stoppSimulering() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    
    public void avsluttProgram() {
        System.exit(0);
    }

    
    public void celleKlikk(int rad, int kol) {
        Celle celle = verden.hentRutenett().hentCelle(rad, kol);

        if (celle.erLevende()) {
            celle.settDoed();
        }
        else {
            celle.settLevende();
        }

        view.oppdaterGrid();
    }
}
