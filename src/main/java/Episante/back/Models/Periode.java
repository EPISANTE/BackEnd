package Episante.back.Models;


public enum Periode {
    MATIN(9),
    APRES_MIDI(14);

    private final int heureDebut;

    Periode(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureDebut() {
        return heureDebut;
    }
}