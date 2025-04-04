package Episante.back.test;

import java.util.Scanner;

public class Diagnositc1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Level 1
        System.out.println("Do you have a fever? (yes or no)");
        String fever = scanner.nextLine().toLowerCase();

        if (fever.equals("yes")) {
            // Level 2a
            System.out.println("Do you have a cough? (yes or no)");
            String cough = scanner.nextLine().toLowerCase();
            if (cough.equals("yes")) {
                // Level 3a
                System.out.println("Do you have shortness of breath? (yes or no)");
                String shortnessBreath = scanner.nextLine().toLowerCase();
                if (shortnessBreath.equals("yes")) {
                    // Level 4a
                    System.out.println("Have you experienced loss of taste or smell? (yes or no)");
                    String loss = scanner.nextLine().toLowerCase();
                    if (loss.equals("yes")) {
                        // Level 5a
                        System.out.println("Do you have fatigue? (yes or no)");
                        String fatigue = scanner.nextLine().toLowerCase();
                        if (fatigue.equals("yes")) {
                            // Level 6a
                            System.out.println("Have you been in contact with someone who tested positive for COVID-19? (yes or no)");
                            String contact = scanner.nextLine().toLowerCase();
                            if (contact.equals("yes")) {
                                System.out.println("Diagnosis: Highly likely COVID-19");
                            } else {
                                System.out.println("Diagnosis: Possible COVID-19");
                            }
                        } else {
                            // Level 6b
                            System.out.println("Have you been in contact with someone who tested positive for COVID-19? (yes or no)");
                            String contact = scanner.nextLine().toLowerCase();
                            if (contact.equals("yes")) {
                                System.out.println("Diagnosis: Possible COVID-19");
                            } else {
                                System.out.println("Diagnosis: Likely COVID-19 (milder presentation)");
                            }
                        }
                    } else {
                        // Level 5b
                        System.out.println("Do you have body aches? (yes or no)");
                        String bodyAches = scanner.nextLine().toLowerCase();
                        if (bodyAches.equals("yes")) {
                            // Level 6c
                            System.out.println("Did your symptoms start suddenly? (yes or no)");
                            String suddenOnset = scanner.nextLine().toLowerCase();
                            if (suddenOnset.equals("yes")) {
                                System.out.println("Diagnosis: Possible Flu");
                            } else {
                                System.out.println("Diagnosis: Possible other viral infection");
                            }
                        } else {
                            // Level 6d
                            System.out.println("Do you have a headache? (yes or no)");
                            String headache = scanner.nextLine().toLowerCase();
                            if (headache.equals("yes")) {
                                System.out.println("Diagnosis: Possible mild Flu or other");
                            } else {
                                System.out.println("Diagnosis: Possible other condition");
                            }
                        }
                    }
                } else {
                    // Level 4b
                    System.out.println("Do you have body aches? (yes or no)");
                    String bodyAches = scanner.nextLine().toLowerCase();
                    if (bodyAches.equals("yes")) {
                        // Level 5c
                        System.out.println("Did your symptoms start suddenly? (yes or no)");
                        String suddenOnset = scanner.nextLine().toLowerCase();
                        if (suddenOnset.equals("yes")) {
                            // Level 6e
                            System.out.println("Do you have chills? (yes or no)");
                            String chills = scanner.nextLine().toLowerCase();
                            if (chills.equals("yes")) {
                                System.out.println("Diagnosis: Highly likely Flu");
                            } else {
                                System.out.println("Diagnosis: Possible Flu");
                            }
                        } else {
                            // Level 6f
                            System.out.println("Do you have chills? (yes or no)");
                            String chills = scanner.nextLine().toLowerCase();
                            if (chills.equals("yes")) {
                                System.out.println("Diagnosis: Possible Flu");
                            } else {
                                System.out.println("Diagnosis: Possible mild Flu or other viral infection");
                            }
                        }
                    } else {
                        // Level 5d
                        System.out.println("Do you have a sore throat? (yes or no)");
                        String soreThroat = scanner.nextLine().toLowerCase();
                        if (soreThroat.equals("yes")) {
                            // Level 6g
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged viral infection");
                            } else {
                                System.out.println("Diagnosis: Possible mild Flu or other");
                            }
                        } else {
                            // Level
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Likely not a viral infection");
                            }
                        }
                    }
                }
            } else {
                // Level 3b
                System.out.println("Do you have body aches? (yes or no)");
                String bodyAches = scanner.nextLine().toLowerCase();
                if (bodyAches.equals("yes")) {
                    // Level 4c
                    System.out.println("Do you have a headache? (yes or no)");
                    String headache = scanner.nextLine().toLowerCase();
                    if (headache.equals("yes")) {
                        // Level 5e
                        System.out.println("Do you have fatigue? (yes or no)");
                        String fatigue = scanner.nextLine().toLowerCase();
                        if (fatigue.equals("yes")) {
                            // Level 6i
                            System.out.println("Did your symptoms start suddenly? (yes or no)");
                            String suddenOnset = scanner.nextLine().toLowerCase();
                            if (suddenOnset.equals("yes")) {
                                System.out.println("Diagnosis: Possible Flu");
                            } else {
                                System.out.println("Diagnosis: Possible other viral infection");
                            }
                        } else {
                            // Level 6j
                            System.out.println("Did your symptoms start suddenly? (yes or no)");
                            String suddenOnset = scanner.nextLine().toLowerCase();
                            if (suddenOnset.equals("yes")) {
                                System.out.println("Diagnosis: Possible mild Flu");
                            } else {
                                System.out.println("Diagnosis: Possible other condition");
                            }
                        }
                    } else {
                        // Level 5f
                        System.out.println("Do you have a sore throat? (yes or no)");
                        String soreThroat = scanner.nextLine().toLowerCase();
                        if (soreThroat.equals("yes")) {
                            // Level 6k
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged viral infection");
                            } else {
                                System.out.println("Diagnosis: Possible mild Flu or other");
                            }
                        } else {
                            // Level 6l
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Likely not a viral infection");
                            }
                        }
                    }
                } else {
                    // Level 4d
                    System.out.println("Do you have a sore throat? (yes or no)");
                    String soreThroat = scanner.nextLine().toLowerCase();
                    if (soreThroat.equals("yes")) {
                        // Level 5g
                        System.out.println("Do you have sneezing? (yes or no)");
                        String sneezing = scanner.nextLine().toLowerCase();
                        if (sneezing.equals("yes")) {
                            // Level 6m
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged Common Cold");
                            } else {
                                System.out.println("Diagnosis: Possible Common Cold");
                            }
                        } else {
                            // Level 6n
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Possible mild viral infection");
                            }
                        }
                    } else {
                        // Level 5h
                        System.out.println("Do you have fatigue? (yes or no)");
                        String fatigue = scanner.nextLine().toLowerCase();
                        if (fatigue.equals("yes")) {
                            // Level 6o
                            System.out.println("Do you have a headache? (yes or no)");
                            String headache = scanner.nextLine().toLowerCase();
                            if (headache.equals("yes")) {
                                System.out.println("Diagnosis: Possible other viral infection");
                            } else {
                                System.out.println("Diagnosis: Possible mild condition");
                            }
                        } else {
                            // Level 6p
                            System.out.println("Do you have a headache? (yes or no)");
                            String headache = scanner.nextLine().toLowerCase();
                            if (headache.equals("yes")) {
                                System.out.println("Diagnosis: Possible mild condition");
                            } else {
                                System.out.println("Diagnosis: Likely not a viral infection");
                            }
                        }
                    }
                }
            }
        } else {
            // Level 2b
            System.out.println("Do you have a runny nose? (yes or no)");
            String runnyNose = scanner.nextLine().toLowerCase();
            if (runnyNose.equals("yes")) {
                // Level 3c
                System.out.println("Do you have a sore throat? (yes or no)");
                String soreThroat = scanner.nextLine().toLowerCase();
                if (soreThroat.equals("yes")) {
                    // Level 4e
                    System.out.println("Do you have sneezing? (yes or no)");
                    String sneezing = scanner.nextLine().toLowerCase();
                    if (sneezing.equals("yes")) {
                        // Level 5i
                        System.out.println("Do you have fatigue? (yes or no)");
                        String fatigue = scanner.nextLine().toLowerCase();
                        if (fatigue.equals("yes")) {
                            // Level 6q
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged Common Cold");
                            } else {
                                System.out.println("Diagnosis: Likely Common Cold");
                            }
                        } else {
                            // Level 6r
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged Common Cold");
                            } else {
                                System.out.println("Diagnosis: Highly likely Common Cold");
                            }
                        }
                    } else {
                        // Level 5j
                        System.out.println("Do you have a headache? (yes or no)");
                        String headache = scanner.nextLine().toLowerCase();
                        if (headache.equals("yes")) {
                            // Level 6s
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged viral infection");
                            } else {
                                System.out.println("Diagnosis: Possible Common Cold or mild condition");
                            }
                        } else {
                            // Level 6t
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Likely Common Cold");
                            }
                        }
                    }
                } else {
                    // Level 4f
                    System.out.println("Do you have sneezing? (yes or no)");
                    String sneezing = scanner.nextLine().toLowerCase();
                    if (sneezing.equals("yes")) {
                        // Level 5k
                        System.out.println("Do you have fatigue? (yes or no)");
                        String fatigue = scanner.nextLine().toLowerCase();
                        if (fatigue.equals("yes")) {
                            // Level 6u
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged Common Cold");
                            } else {
                                System.out.println("Diagnosis: Possible Common Cold");
                            }
                        } else {
                            // Level 6v
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged mild condition");
                            } else {
                                System.out.println("Diagnosis: Likely Common Cold");
                            }
                        }
                    } else {
                        // Level 5l
                        System.out.println("Do you have a headache? (yes or no)");
                        String headache = scanner.nextLine().toLowerCase();
                        if (headache.equals("yes")) {
                            // Level 6w
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Possible mild viral infection");
                            }
                        } else {
                            // Level 6x
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Likely not a viral infection");
                            }
                        }
                    }
                }
            } else {
                // Level 3d
                System.out.println("Do you have fatigue? (yes or no)");
                String fatigue = scanner.nextLine().toLowerCase();
                if (fatigue.equals("yes")) {
                    // Level 4g
                    System.out.println("Do you have a headache? (yes or no)");
                    String headache = scanner.nextLine().toLowerCase();
                    if (headache.equals("yes")) {
                        // Level 5m
                        System.out.println("Do you have body aches? (yes or no)");
                        String bodyAches = scanner.nextLine().toLowerCase();
                        if (bodyAches.equals("yes")) {
                            // Level 6y
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged viral infection");
                            } else {
                                System.out.println("Diagnosis: Possible mild Flu or other");
                            }
                        } else {
                            // Level 6z
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Possible mild viral infection");
                            }
                        }
                    } else {
                        // Level 5n
                        System.out.println("Do you have a sore throat? (yes or no)");
                        String soreThroat = scanner.nextLine().toLowerCase();
                        if (soreThroat.equals("yes")) {
                            // Level 6aa
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged mild condition");
                            } else {
                                System.out.println("Diagnosis: Possible mild viral infection");
                            }
                        } else {
                            // Level 6ab
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Likely not a viral infection");
                            }
                        }
                    }
                } else {
                    // Level 4h
                    System.out.println("Do you have a headache? (yes or no)");
                    String headache = scanner.nextLine().toLowerCase();
                    if (headache.equals("yes")) {
                        // Level 5o
                        System.out.println("Do you have a sore throat? (yes or no)");
                        String soreThroat = scanner.nextLine().toLowerCase();
                        if (soreThroat.equals("yes")) {
                            // Level 6ac
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged mild condition");
                            } else {
                                System.out.println("Diagnosis: Possible mild viral infection");
                            }
                        } else {
                            // Level 6ad
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Possible mild condition");
                            }
                        }
                    } else {
                        // Level 5p
                        System.out.println("Do you have sneezing? (yes or no)");
                        String sneezing = scanner.nextLine().toLowerCase();
                        if (sneezing.equals("yes")) {
                            // Level 6ae
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible prolonged Common Cold");
                            } else {
                                System.out.println("Diagnosis: Possible Common Cold");
                            }
                        } else {
                            // Level 6af
                            System.out.println("Have your symptoms lasted more than 10 days? (yes or no)");
                            String duration = scanner.nextLine().toLowerCase();
                            if (duration.equals("yes")) {
                                System.out.println("Diagnosis: Possible other condition");
                            } else {
                                System.out.println("Diagnosis: Likely not a viral infection");
                            }
                        }
                    }
                }
            }
        }

        scanner.close();

    }
}
