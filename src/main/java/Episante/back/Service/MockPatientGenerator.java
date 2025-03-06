package Episante.back.Service;

import Episante.back.Models.Patient;
import Episante.back.Models.Sexe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockPatientGenerator {

    private static final Random rand = new Random();
    private static int compteurMail = 1;



    private static void setCompteurMail() {
        compteurMail++;
    }

    private static DecimalFormat df = new DecimalFormat("#.#");
    private static final double imcMoy = 24.5;
    private static final double eTypeImc = 3.0;
    private static final int tailleMoyH = 175;
    private static final int tailleMoyF = 165;
    private static final int eTypeTaille = 15;


    private static final String[] PRENOMSH = {"Anir", "Ayoub","Julien", "Lucas", "Lamine", "Pablo", "Paul", "Antoine"};
    private static final String[] PRENOMSF = {"Ikram", "Marie", "Ines", "Sophie", "Lina", "Julie","Alex","Alicia"};
    private static final String[] NOMS = {"Yamal", "Griezman", "Giroud", "Chamakh", "Regragui", "Suarez","Rodriguez","Alvarez"};
    private static final String[] ADRESSES = {"Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes"};



    private static double nextGaussian(double mean, double stdDev, double min, double max) {
        Random rand = new Random();
        double value = mean + rand.nextGaussian() * stdDev;

        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }


    private static String generateUniqueEmail() {
        return "patient" + compteurMail + "@episante.com";
    }

    private static String generatePhoneNumber() {
        return "06" + String.format("%08d", rand.nextInt(100000000));
    }

    public static List<Patient> generatePatients(int nombre, PatientService patientService) {
        List<Patient> patients = new ArrayList<>();


        double imc = nextGaussian(imcMoy, eTypeImc, 16.0, 40.0);
        int heightH = (int) nextGaussian(tailleMoyH, eTypeTaille, 150, 210);
        int heightF = (int) nextGaussian(tailleMoyF, eTypeTaille, 140, 190);
        double weightH = imc * Math.pow((heightH / 100.0), 2);
        double weightF = imc * Math.pow((heightF / 100.0), 2);

        for(int i = 0; i < nombre; i++) {
            Patient patient = new Patient();
            patient.setNom(NOMS[rand.nextInt(NOMS.length)]);
            patient.setAge(String.valueOf(rand.nextInt(55) + 16));
            patient.setAdresse(ADRESSES[rand.nextInt(ADRESSES.length)]);
            patient.setEmail(generateUniqueEmail());
            patient.setMdp("123456");
            patient.setTelephone(generatePhoneNumber());
            patient.setSexe(rand.nextBoolean() ? Sexe.HOMME : Sexe.FEMME);
            if (patient.getSexe() == Sexe.HOMME) {
                patient.setPrenom(PRENOMSH[rand.nextInt(PRENOMSH.length)]);
                patient.setTaille(String.valueOf(heightH));
                patient.setPoids(df.format(weightH));
            } else {
                patient.setPrenom(PRENOMSF[rand.nextInt(PRENOMSF.length)]);
                patient.setTaille(String.valueOf(heightF));
                patient.setPoids(df.format(weightF));
            }
            patientService.add(patient);
            patients.add(patient);
            setCompteurMail();
        }
        return patients;
    }


}