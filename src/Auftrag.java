public class Auftrag {
    private int eingangsZeitPunkt;
    private int auftragsDauer;
    private int warteZeit;
    private int fertigStellung;

    public Auftrag(int eingangsZeitPunkt, int auftragsDauer) {
        this.eingangsZeitPunkt = eingangsZeitPunkt;
        this.auftragsDauer = auftragsDauer;
    }

    public int getEingangsZeitPunkt() {
        return eingangsZeitPunkt;
    }

    public int getAuftragsDauer() {
        return auftragsDauer;
    }

    public int getWarteZeit() {
        return warteZeit;
    }

    public int getFertigStellung() {
        return fertigStellung;
    }

    public void setWarteZeit(int warteZeit) {
        this.warteZeit = warteZeit;
    }

    public void setFertigStellung(int fertigStellung) {
        this.fertigStellung = fertigStellung;
    }

    @Override
    public String toString() {
        return eingangsZeitPunkt + " " + auftragsDauer + " " + warteZeit + " " + fertigStellung;
    }
}
