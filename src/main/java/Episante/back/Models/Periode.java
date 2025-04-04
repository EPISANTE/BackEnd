package Episante.back.Models;
import java.time.LocalTime;

public enum Periode {
    CRENEAU_09_00(9, 0),
    CRENEAU_09_30(9, 30),
    CRENEAU_10_00(10, 0),
    CRENEAU_10_30(10, 30),
    CRENEAU_11_00(11, 0),
    CRENEAU_11_30(11, 30),
    CRENEAU_14_00(14, 0),
    CRENEAU_14_30(14, 30),
    CRENEAU_15_00(15, 0),
    CRENEAU_15_30(15, 30),
    CRENEAU_16_00(16, 0),
    CRENEAU_16_30(16, 30),
    CRENEAU_17_00(17, 0),
    CRENEAU_17_30(17, 30);

    private final int heure;
    private final int minute;

    Periode(int heure, int minute) {
        this.heure = heure;
        this.minute = minute;
    }

    public LocalTime getHeureDebut() {
        return LocalTime.of(heure, minute);
    }
}
