package Episante.back.test.decisionTree;

import java.util.Objects;
import java.util.Scanner;

public class DecisionTree {

    Node root ;
    public DecisionTree(Node root){
        Objects.requireNonNull(root,"The root node cannot be null");
        this.root = root ;
    }

    public void run() {
        Node currentNode = root;

        try (Scanner scanner = new Scanner(System.in)) {


            while (currentNode instanceof DecisionNode) {
                DecisionNode decisionNode = (DecisionNode) currentNode;

                System.out.println(decisionNode.getQuestion() + " (yes/no):");
                String answer = "";


                boolean validInput = false;
                while (!validInput) {
                    answer = scanner.nextLine().trim().toLowerCase();
                    if (answer.equals("yes") || answer.equals("y")) {
                        currentNode = decisionNode.getYesBranch();
                        validInput = true;
                    } else if (answer.equals("no") || answer.equals("n")) {
                        currentNode = decisionNode.getNoBranch();
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        System.out.println(decisionNode.getQuestion() + " (yes/no):");
                    }
                }
            }


            if (currentNode instanceof LeafNode) {
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

        //final results aka lwri9at
        LeafNode stayInside = new LeafNode("Stay inside and relax.");
        LeafNode goToPark = new LeafNode("Go to the park!");
        LeafNode readBook = new LeafNode("Read a book.");
        LeafNode watchMovie = new LeafNode("Watch a movie.");
        LeafNode goShopping = new LeafNode("Go shopping.");


        DecisionNode boredNode = new DecisionNode("Are you bored?", watchMovie, readBook);
        DecisionNode rainingNode = new DecisionNode("Is it raining?", boredNode, goToPark);
        DecisionNode freeTimeNode = new DecisionNode("Do you have free time?", rainingNode, goShopping);


        Node rootNode = freeTimeNode;

        DecisionTree tree = new DecisionTree(rootNode);

        System.out.println("Welcome! Let's decide what to do.");
        tree.run();
        System.out.println("Decision process complete.");

    }

}
