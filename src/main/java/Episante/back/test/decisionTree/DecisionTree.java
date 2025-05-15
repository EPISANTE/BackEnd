package Episante.back.test.decisionTree;

import java.util.Objects;
import java.util.Scanner;

public class DecisionTree {

    Node root ;// the root node of our decision tree
    public DecisionTree(Node root){
        Objects.requireNonNull(root,"The root node cannot be null");
        this.root = root ;
    }
    // until this point our decision tree has only the root node

    public void run() {
        Node currentNode = root;

        try (Scanner scanner = new Scanner(System.in)) {


            while (currentNode instanceof DecisionNode) { // do this code until the current node is leaf or null maybe
                DecisionNode decisionNode = (DecisionNode) currentNode;

                System.out.println(decisionNode.getQuestion() + " (yes/no):");
                String answer = "";


                boolean validInput = false;
                while (!validInput) { // execute the code in the while loop until the validInput == true
                    answer = scanner.nextLine().trim().toLowerCase();
                    if (answer.equals("yes") || answer.equals("y")) {
                        currentNode = decisionNode.getYesBranch();
                        validInput = true;
                    } else if (answer.equals("no") || answer.equals("n")) {
                        currentNode = decisionNode.getNoBranch();
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        System.out.println(decisionNode.getQuestion() + " (yes/no):"); // ask again
                    }
                }
            }


            if (currentNode instanceof LeafNode) { // which mean the finale decision ==> the result
                LeafNode leafNode = (LeafNode) currentNode;
                System.out.println("-------------------------");
                System.out.println("Result: " + leafNode.getDecision());
                System.out.println("-------------------------");
            } else {


                System.err.println("Error: Traversal ended unexpectedly, not at a leaf node.");
            }
        }
    }

    public static void main(String[] args) {

        // --- thos are the result of any diagnostic ==> terminal nodes ---
        LeafNode leafHighlyLikelyCovid = new LeafNode("Highly likely COVID-19");
        LeafNode leafPossibleCovid = new LeafNode("Possible COVID-19");
        LeafNode leafLikelyCovidMilder = new LeafNode("Likely COVID-19 (milder presentation)");
        LeafNode leafPossibleFlu = new LeafNode("Possible Flu");
        LeafNode leafPossibleOtherViral = new LeafNode("Possible other viral infection");
        LeafNode leafPossibleMildFluOrOther = new LeafNode("Possible mild Flu or other");
        LeafNode leafPossibleOtherCondition = new LeafNode("Possible other condition");
        LeafNode leafHighlyLikelyFlu = new LeafNode("Highly likely Flu");
        LeafNode leafPossibleMildFlu = new LeafNode("Possible mild Flu");
        LeafNode leafPossibleProlongedViral = new LeafNode("Possible prolonged viral infection");
        LeafNode leafLikelyNotViral = new LeafNode("Likely not a viral infection");
        LeafNode leafPossibleProlongedCold = new LeafNode("Possible prolonged Common Cold");
        LeafNode leafPossibleCommonCold = new LeafNode("Possible Common Cold");
        LeafNode leafPossibleMildViral = new LeafNode("Possible mild viral infection");
        LeafNode leafPossibleMildCondition = new LeafNode("Possible mild condition");
        LeafNode leafLikelyCommonCold = new LeafNode("Likely Common Cold");
        LeafNode leafHighlyLikelyCommonCold = new LeafNode("Highly likely Common Cold");
        LeafNode leafPossibleColdOrMild = new LeafNode("Possible Common Cold or mild condition");
        LeafNode leafPossibleProlongedMildCondition = new LeafNode("Possible prolonged mild condition");


        // --- LEVEL 6 NODES ---
        DecisionNode node6aContact = new DecisionNode("Have you been in contact with someone who tested positive for COVID-19?", leafHighlyLikelyCovid, leafPossibleCovid);
        DecisionNode node6bContact = new DecisionNode("Have you been in contact with someone who tested positive for COVID-19?", leafPossibleCovid, leafLikelyCovidMilder);
        DecisionNode node6cSuddenOnset = new DecisionNode("Did your symptoms start suddenly?", leafPossibleFlu, leafPossibleOtherViral);
        DecisionNode node6dHeadache = new DecisionNode("Do you have a headache?", leafPossibleMildFluOrOther, leafPossibleOtherCondition);
        DecisionNode node6eChills = new DecisionNode("Do you have chills?", leafHighlyLikelyFlu, leafPossibleFlu);
        DecisionNode node6fChills = new DecisionNode("Do you have chills?", leafPossibleFlu, leafPossibleMildFluOrOther);
        DecisionNode node6gDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleMildFluOrOther);
        DecisionNode node6hDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6iSuddenOnset = new DecisionNode("Did your symptoms start suddenly?", leafPossibleFlu, leafPossibleOtherViral);
        DecisionNode node6jSuddenOnset = new DecisionNode("Did your symptoms start suddenly?", leafPossibleMildFlu, leafPossibleOtherCondition);
        DecisionNode node6kDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleMildFluOrOther);
        DecisionNode node6lDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6mDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafPossibleCommonCold);
        DecisionNode node6nDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildViral);
        DecisionNode node6oHeadache = new DecisionNode("Do you have a headache?", leafPossibleOtherViral, leafPossibleMildCondition);
        DecisionNode node6pHeadache = new DecisionNode("Do you have a headache?", leafPossibleMildCondition, leafLikelyNotViral);
        DecisionNode node6qDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafLikelyCommonCold);
        DecisionNode node6rDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafHighlyLikelyCommonCold);
        DecisionNode node6sDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleColdOrMild);
        DecisionNode node6tDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyCommonCold);
        DecisionNode node6uDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafPossibleCommonCold);
        DecisionNode node6vDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedMildCondition, leafLikelyCommonCold);
        DecisionNode node6wDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildViral);
        DecisionNode node6xDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6yDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleMildFluOrOther);
        DecisionNode node6zDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildViral);
        DecisionNode node6aaDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedMildCondition, leafPossibleMildViral);
        DecisionNode node6abDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6acDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedMildCondition, leafPossibleMildViral);
        DecisionNode node6adDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildCondition);
        DecisionNode node6aeDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafPossibleCommonCold);
        DecisionNode node6afDuration = new DecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);

        // --- LEVEL 5 NODES ---
        DecisionNode node5aFatigue = new DecisionNode("Do you have fatigue?", node6aContact, node6bContact);
        DecisionNode node5bBodyAches = new DecisionNode("Do you have body aches?", node6cSuddenOnset, node6dHeadache);
        DecisionNode node5cSuddenOnset = new DecisionNode("Did your symptoms start suddenly?", node6eChills, node6fChills);
        DecisionNode node5dSoreThroat = new DecisionNode("Do you have a sore throat?", node6gDuration, node6hDuration);
        DecisionNode node5eFatigue = new DecisionNode("Do you have fatigue?", node6iSuddenOnset, node6jSuddenOnset);
        DecisionNode node5fSoreThroat = new DecisionNode("Do you have a sore throat?", node6kDuration, node6lDuration);
        DecisionNode node5gSneezing = new DecisionNode("Do you have sneezing?", node6mDuration, node6nDuration);
        DecisionNode node5hFatigue = new DecisionNode("Do you have fatigue?", node6oHeadache, node6pHeadache);
        DecisionNode node5iFatigue = new DecisionNode("Do you have fatigue?", node6qDuration, node6rDuration);
        DecisionNode node5jHeadache = new DecisionNode("Do you have a headache?", node6sDuration, node6tDuration);
        DecisionNode node5kFatigue = new DecisionNode("Do you have fatigue?", node6uDuration, node6vDuration);
        DecisionNode node5lHeadache = new DecisionNode("Do you have a headache?", node6wDuration, node6xDuration);
        DecisionNode node5mBodyAches = new DecisionNode("Do you have body aches?", node6yDuration, node6zDuration);
        DecisionNode node5nSoreThroat = new DecisionNode("Do you have a sore throat?", node6aaDuration, node6abDuration);
        DecisionNode node5oSoreThroat = new DecisionNode("Do you have a sore throat?", node6acDuration, node6adDuration);
        DecisionNode node5pSneezing = new DecisionNode("Do you have sneezing?", node6aeDuration, node6afDuration);

        // --- LEVEL 4 NODES ---
        DecisionNode node4aLoss = new DecisionNode("Have you experienced loss of taste or smell?", node5aFatigue, node5bBodyAches);
        DecisionNode node4bBodyAches = new DecisionNode("Do you have body aches?", node5cSuddenOnset, node5dSoreThroat);
        DecisionNode node4cHeadache = new DecisionNode("Do you have a headache?", node5eFatigue, node5fSoreThroat);
        DecisionNode node4dSoreThroat = new DecisionNode("Do you have a sore throat?", node5gSneezing, node5hFatigue);
        DecisionNode node4eSneezing = new DecisionNode("Do you have sneezing?", node5iFatigue, node5jHeadache);
        DecisionNode node4fSneezing = new DecisionNode("Do you have sneezing?", node5kFatigue, node5lHeadache);
        DecisionNode node4gHeadache = new DecisionNode("Do you have a headache?", node5mBodyAches, node5nSoreThroat);
        DecisionNode node4hHeadache = new DecisionNode("Do you have a headache?", node5oSoreThroat, node5pSneezing);

        // --- LEVEL 3 NODES ---
        DecisionNode node3aShortnessBreath = new DecisionNode("Do you have shortness of breath?", node4aLoss, node4bBodyAches);
        DecisionNode node3bBodyAches = new DecisionNode("Do you have body aches?", node4cHeadache, node4dSoreThroat);
        DecisionNode node3cSoreThroat = new DecisionNode("Do you have a sore throat?", node4eSneezing, node4fSneezing);
        DecisionNode node3dFatigue = new DecisionNode("Do you have fatigue?", node4gHeadache, node4hHeadache);

        // --- LEVEL 2 NODES ---
        DecisionNode node2aCough = new DecisionNode("Do you have a cough?", node3aShortnessBreath, node3bBodyAches);
        DecisionNode node2bRunnyNose = new DecisionNode("Do you have a runny nose?", node3cSoreThroat, node3dFatigue);

        // --- LEVEL 1 NODE (ROOT) ---
        DecisionNode rootNode = new DecisionNode("Do you have a fever?", node2aCough, node2bRunnyNose);


        DecisionTree tree = new DecisionTree(rootNode);

        System.out.println("Welcome! Let's try to identify possible conditions based on your symptoms.");
        System.out.println("Please answer the following questions with 'yes' or 'no'.");
        System.out.println("---------------------------------------------------------------------");

        tree.run();

        System.out.println("Diagnosis process complete. Remember to consult a healthcare professional.");

    }

}
