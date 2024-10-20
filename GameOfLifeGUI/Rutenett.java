package GameOfLifeGUI;
// konstruktør
class Rutenett {
    int antRader;
    int antKolonner;
    Celle[][] rutene;

    public Rutenett(int rader, int kolonner) {
        antRader = rader;
        antKolonner = kolonner;
        rutene = new Celle[antRader][antKolonner];  
    }
// setter 1/3 av cellene i rutenett til levende
    public void lagCelle(int rad, int kol) {
        double sjanse = Math.random();
        Celle celle = new Celle();

        if (sjanse <= 0.3333) {
            celle.settLevende(); 
        }

        rutene[rad][kol] = celle;
    }

// går gjennom rutenett og fyller den med celler ved bruk av lagCelle metoden.
    public void fyllMedTilfeldigeCeller() {
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                lagCelle(r, k);
            }
        }
    }

// lar deg oppgi koordinater for å hente ut ønskede celle, og returner null hvis oppgitt rad/kolonne er ulovlig
    public Celle hentCelle(int rad, int kol) {
        if (rad >= antRader || rad < 0 || kol >= antKolonner || kol < 0) {
            return null;
        } else {
            return rutene[rad][kol];
        }
        
    }

// skriver ut rutenett med dobbel for løkke.
// prøve å få det til slik som vist i PDFen, men får det ikke til. ønsker gjerne tilbakemelding
    public void tegnRutenett() {
        for (int r = 0; r < antRader; r++) {
            System.out.println("  ");
            for (int k  = 0; k < antKolonner; k++) {
                System.out.print("| " + rutene[r][k].hentStatusTegn() + " |");         
            }
        }
    }

// setter referanser til naboer ved å sjekke at celle ikke er null, hente ut celle + naboer
// så bruke en if sjekk for å si nabo ikke er 0/cellen og ikke null.
    public void settNaboer(int rad, int kol) {
        Celle celle = hentCelle(rad, kol);

        if (celle != null) {
            for (int r = -1; r < 2; r++) {
                for (int k = -1; k < 2; k++) {
                    Celle nabo = hentCelle(rad + r, kol + k);
                    if (nabo != null && nabo != rutene[rad + 0][kol + 0]) {
                        celle.leggTilNabo(hentCelle(rad + r, kol + k));
                    }
                }
            }
        }
        
    }

// bruker settNaboer på alle elementer i rutenet
    public void kobleAlleCeller() {
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                settNaboer(r, k);
            }
        }
    }

// teller antall levende i rutenett med en teller
    public int antallLevende() {
        int teller = 0;

        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                Celle celle = hentCelle(r, k);
                if (celle.erLevende()) {
                    teller++;
                }
        
            } 
        }

        return teller;
    }
    
}