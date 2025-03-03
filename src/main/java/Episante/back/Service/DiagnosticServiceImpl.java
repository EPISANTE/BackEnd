package Episante.back.Service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {

    private final Scanner scanner = new Scanner(System.in);

    private boolean askQuestion(String question) {
        System.out.println(question);
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("Please answer 'yes' or 'no':");
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.equals("yes");
    }

    @Override
    public void startDiagnosis() {
        System.out.println("Welcome to the Medical Diagnostic System");
        System.out.println("I'll ask you some questions to help identify your illness.");

        boolean hasFever = askQuestion("Do you have fever? yes/no");

        if (hasFever) {
            boolean hasFatigueOrMusclePain = askQuestion("Do you have fatigue or muscle pain? (yes/no)");

            if (hasFatigueOrMusclePain) {
                boolean hasCough = askQuestion("Do you have a cough? (yes/no)");

                if (hasCough) {
                    System.out.println("Diagnosis: Possible Flu (Grippe)");
                } else {
                    boolean hasRunnyNose = askQuestion("Do you have a runny nose? (yes/no)");

                    if (hasRunnyNose) {
                        System.out.println("Diagnosis: Possible Common Cold (Rhume)");
                    } else {
                        System.out.println("Diagnosis: Possible COVID-19");
                    }
                }

            }else {
                System.out.println("Diagnosis: Possible Common Cold (Rhume)");
            }
        }else {
            System.out.println("Insufficient symptoms for a diagnosis. Please consult a doctor if you feel unwell.");
        }
        scanner.close();
    }
}