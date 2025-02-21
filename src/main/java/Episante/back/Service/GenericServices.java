package Episante.back.Service;

import java.lang.reflect.Field;
import java.util.Map;



public class GenericServices {
    public static <T> String miseAJourGenerique(T cible, Map<String, Object> MAJ) {
        StringBuilder resultats = new StringBuilder("Résultats de la mise à jour :\n");

        for (Map.Entry<String, Object> entree : MAJ.entrySet()) {
            Field champ;
            try {
                champ = trouverChamp(cible.getClass(), entree.getKey());

                if (champ == null) {
                    resultats.append("Champ introuvable : ").append(entree.getKey()).append("\n");
                    continue;
                }

                champ.setAccessible(true);

                if (!champ.getType().isAssignableFrom(entree.getValue().getClass())) {
                    resultats.append("Type invalide pour le champ ").append(entree.getKey())
                            .append(" : attendu ").append(champ.getType().getSimpleName())
                            .append(", reçu ").append(entree.getValue().getClass().getSimpleName()).append("\n");
                    continue;
                }

                champ.set(cible, entree.getValue());
                resultats.append("Champ ").append(entree.getKey()).append(" mis à jour avec succès.\n");

            } catch (IllegalAccessException e) {
                resultats.append("Accès refusé pour le champ : ").append(entree.getKey()).append("\n");
            } catch (Exception e) {
                resultats.append("Erreur inattendue avec le champ ").append(entree.getKey())
                        .append(" : ").append(e.getMessage()).append("\n");
            }
        }

        return resultats.toString();
    }

    // Méthode pour rechercher un champ dans les classes parent
    private static Field trouverChamp(Class<?> classe, String nomChamp) {
        while (classe != null) {
            try {
                return classe.getDeclaredField(nomChamp);
            } catch (NoSuchFieldException e) {
                // Continuer à rechercher dans la classe parent
                classe = classe.getSuperclass();
            }
        }
        return null;
    }




}
