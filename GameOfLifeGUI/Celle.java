package GameOfLifeGUI;
class Celle {
    boolean levende;
    Celle[] naboer;
    int antNaboer;
    int antLevendeNaboer;

    public Celle() {
    levende = false;
    naboer = new Celle[8];
    antNaboer = 0;
    antLevendeNaboer = 0;
    }
    
// setter status til død eller levende
    public void settDoed() {
        levende = false;
    }

    public void settLevende() {
        levende = true;
    }

    public boolean erLevende() {
        return levende;
    }
// bruker java short hand for if else til å returnere O hvis true eller . hvis false 
    public char hentStatusTegn() {
        return levende ? 'O' : '.';
    }

// finner tomt element i array og legger til celle objekt
    public void leggTilNabo(Celle nabo) {
        for (int i = 0; i < naboer.length ; i++) {
            if (naboer[i] == null) {
                naboer[i] = nabo;

                antNaboer++;

                return;
            }
        }
    }

// går gjennom naboer, sjekker at element ikke er null og er levende, og øker antLevendeNaboer
    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;

        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] != null && naboer[i].erLevende() ) {
                antLevendeNaboer++;
            }
        }
    }

// forandrer status på celle ut i fra spillets regler
    public void oppdaterStatus() {
        if (erLevende()) {
            if ((antLevendeNaboer < 2) || (antLevendeNaboer > 3) ) {
                settDoed();
            }
        } else {
            if (antLevendeNaboer == 3) {
                settLevende();
            }
        }
    }
}

 

