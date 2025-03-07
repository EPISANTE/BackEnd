package Episante.back;
import Episante.back.Models.DecisionTreeNode;
import java.util.HashMap;
import java.util.Map;

public class DecisionTreeBuilder {

    public static Map<Long, DecisionTreeNode> buildIllnessTree() {
        Map<Long, DecisionTreeNode> tree = new HashMap<>();

        DecisionTreeNode covid = new DecisionTreeNode("ANSWER", "Possible Covid 19", null, null);
        DecisionTreeNode rhume = new DecisionTreeNode("ANSWER", "Possible Rhume", null, null);
        DecisionTreeNode grippe = new DecisionTreeNode("ANSWER", "Possible Grippe", null, null);

        tree.put(1L, covid);
        tree.put(2L, rhume);
        tree.put(3L, grippe);

        DecisionTreeNode nezCoule = new DecisionTreeNode("QUESTION", "Nez coule?", 2L, 1L);
        tree.put(4L, nezCoule);

        DecisionTreeNode laToux = new DecisionTreeNode("QUESTION", "La Toux?", 4L, 3L);
        tree.put(5L, laToux);


        DecisionTreeNode fatigue = new DecisionTreeNode("QUESTION", "Fatigue ou douleurs musculaires?", 5L, 2L);
        tree.put(6L, fatigue);

        DecisionTreeNode fievre = new DecisionTreeNode("QUESTION", "Fièvre?", 6L, 2L);
        tree.put(7L, fievre);

        DecisionTreeNode start = new DecisionTreeNode("QUESTION", "Start", 7L, 7L);
        tree.put(8L, start);


        return tree;
    }

    public static DecisionTreeNode getRootNode(Map<Long, DecisionTreeNode> tree) {

        return tree.get(8L);
    }

    public static void main(String[] args) {
        Map<Long, DecisionTreeNode> illnessTree = buildIllnessTree();
        DecisionTreeNode root = getRootNode(illnessTree);

        System.out.println("Illness Decision Tree:");

        printTree(illnessTree, root, "");

    }
    public static void printTree(Map<Long, DecisionTreeNode> tree, DecisionTreeNode node, String indent) {
        if (node == null) {
            return;
        }

        System.out.println(indent + node.getText());

        if (node.getType().equals("QUESTION")) {
            DecisionTreeNode yesNode = tree.get(node.getYesNodeId());
            DecisionTreeNode noNode = tree.get(node.getNoNodeId());

            System.out.println(indent + "  Yes -> ");
            printTree(tree, yesNode, indent + "    ");
            System.out.println(indent + "  No -> ");
            printTree(tree, noNode, indent + "    ");
        }
    }
}