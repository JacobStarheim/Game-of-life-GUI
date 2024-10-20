package GameOfLifeGUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLifeView {
    JFrame vindu;
    JPanel grid;
    JButton startKnapp;
    JButton stoppKnapp;
    JButton avsluttKnapp;
    JLabel antLevendeCeller;
    Verden verden;
    GameOfLifeKontroller kontroller;

    public GameOfLifeView(Verden verden, GameOfLifeKontroller kontroller) {
        this.verden = verden;
        this.kontroller = kontroller;
        lagGui();
    }

    public void lagGui() {

        // crossplatform design
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        // vindu
        vindu = new JFrame("Conway´s Game of Life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // vindu lukkes helt
        vindu.setLayout(new BorderLayout());    


        //knapper
        JPanel knappPanel = new JPanel();

        //start knapp
        startKnapp = new JButton("Start");
        class StartBehandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.startSimulering();
            }
        }
        startKnapp.addActionListener(new StartBehandler());
        knappPanel.add(startKnapp);

        //stoppknapp
        stoppKnapp = new JButton("Stopp");
        class StoppBehandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.stoppSimulering();
            }
        }
        stoppKnapp.addActionListener(new StoppBehandler());
        knappPanel.add(stoppKnapp);

        //avslutt knapp
        avsluttKnapp = new JButton("Avslutt");
        class AvsluttBehandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.avsluttProgram();
            }
        }
        avsluttKnapp.addActionListener(new AvsluttBehandler());
        knappPanel.add(avsluttKnapp);

        //JLabel
        antLevendeCeller = new JLabel();
        knappPanel.add(antLevendeCeller);

        // grid
        grid = new JPanel();
        oppdaterGrid();
        vindu.add(grid, BorderLayout.CENTER);

        // posisjon, pakking og synlighet
        vindu.add(knappPanel, BorderLayout.NORTH);
        vindu.pack();
        vindu.setVisible(true);
    }

    public void oppdaterGrid() {
        grid.removeAll();       // fjerner cellene
        grid.setLayout(new GridLayout(verden.antRader, verden.antKolonner));
        Rutenett rutenett = verden.hentRutenett();

        for (int r = 0; r < verden.antRader; r++) {
            for (int k = 0; k < verden.antKolonner; k++) {
                Celle celle = rutenett.hentCelle(r, k);
                JButton celleKnapp = new JButton();

                // celleknapp blir satt svart hvis død og hvit hvis levende
                celleKnapp.setBackground(celle.erLevende() ? Color.BLACK : Color.WHITE);
                celleKnapp.addActionListener(new CelleKnappBehandler(r, k));
                grid.add(celleKnapp);

            }
        }

        antLevendeCeller.setText("antall levende celler: " + verden.hentRutenett().antallLevende() + "\n Gen nr: " + verden.genNr);

        grid.revalidate();
        grid.repaint();
    }

    class CelleKnappBehandler implements ActionListener {
        int rad;
        int kol;
    
        public CelleKnappBehandler(int rad, int kol) {
            this.rad = rad;
            this.kol = kol;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            kontroller.celleKlikk(rad, kol);
        }
    }
}

/*class GameOfLifeKontroller {
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
}*/

/*class GameOfLifeGUI {
    public static void main(String[] args) {
        Verden verden = new Verden(80, 60);
        GameOfLifeKontroller kontroller = new GameOfLifeKontroller(verden);
        kontroller.start();
    }
}*/

