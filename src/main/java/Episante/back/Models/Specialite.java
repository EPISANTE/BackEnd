package Episante.back.Models;

public enum Specialite {
    CARDIOLOGIE,
    DERMATOLOGIE,
    GYNECOLOGIE,
    PEDIATRIE,
    ORTHOPEDIE,
    GENERALISTE,
    OPHTALMOLOGIE,
    NEUROLOGIE,
    ENDOCRINOLOGIE,
    PSYCHIATRIE;

    public static Specialite fromString(String value) {
        return valueOf(value.toUpperCase());
    }

    public String getLibelle() {
        return name();
    }
}