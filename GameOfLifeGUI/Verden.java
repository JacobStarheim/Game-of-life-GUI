package GameOfLifeGUI;
class Verden {
    int antRader;
    int antKolonner;
    Rutenett rutenett;
    int genNr;

    public Verden(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        genNr = 0;
    }

    public void tegn() {
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

// oppdaterer rutenett ved å telle levende naboer for hver celle og
// sjekker status til celle. genNr økes ++ for hver generasjon.
    public void oppdatering() {
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                Celle celle = rutenett.hentCelle(r, k);
                celle.tellLevendeNaboer();
                celle.oppdaterStatus();
            }
       }

       genNr++;
    }

    public Rutenett hentRutenett() {
        return rutenett;
    }
}
