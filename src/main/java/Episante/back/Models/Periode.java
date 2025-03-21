package Episante.back.Models;


import java.time.LocalTime;

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