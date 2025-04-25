package Episante.back.Service;

import Episante.back.Models.DecisionNode;
import Episante.back.Models.Node ;
import Episante.back.Models.LeafNode ;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Getter
public class DecisionTreeServiceImp {
    private static final Logger log = LoggerFactory.getLogger(DecisionTreeServiceImp.class);

    private Node rootNode;

    private final Map<String, Node> nodeMap = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initializeTree() {
        log.info("Initialize Decision Tree from the Json...");
        nodeMap.clear(); // toensure that our map is clear before loading

        try{
            //TODO 1 :  Load the JSON file from the classpath and get and IS from it
            Resource resource = resourceLoader.getResource("src/main/resources/decision-tree.json");
            InputStream jsonIS = resource.getInputStream();
            if (jsonIS == null){
                throw new IOException("Cannot find the decision tree in the class path");
            }

            //TODO 2 : Deserialize the JSON into the Node object graph
            this.rootNode = objectMapper.readValue(jsonIS , Node.class);

            // TODO 3: Populate the nodeMap

            // TODO 4: Implement the recursive helper method for populating the nodeMap





        }catch (IOException e){
            log.error("Failed to load or parse the DT json " , e);
        }

    }

    private DecisionNode createDecisionNode(String question, Node yesBranch, Node noBranch) {
        DecisionNode node = new DecisionNode(question, yesBranch, noBranch);
        nodeMap.put(node.getId(), node);
        addNodeToMapIfNeeded(yesBranch);
        addNodeToMapIfNeeded(noBranch);
        return node;
    }

    private void addNodeToMapIfNeeded(Node node) {
        if (node instanceof DecisionNode) {
            DecisionNode decisionNode = (DecisionNode) node;
            nodeMap.putIfAbsent(decisionNode.getId(), decisionNode);
        }
    }


    public Optional<Node> getNextNode(String currentNodeId, String answer) {
        System.out.println("--- getNextNode ---");
        System.out.println("Received Node ID: [" + currentNodeId + "]");
        System.out.println("Received Answer: [" + answer + "]");

        Node nodeFromMap = nodeMap.get(currentNodeId);

        if (!(nodeFromMap instanceof DecisionNode)) {
            System.err.println("ERROR: Node ID not found in map or node is not a DecisionNode! ID: [" + currentNodeId + "]");
            System.err.println("Available DecisionNode IDs in map:");
            nodeMap.entrySet().stream()
                    .filter(entry -> entry.getValue() instanceof DecisionNode)
                    .forEach(entry -> System.err.println("- " + entry.getKey()));
            return Optional.empty();
        }

        DecisionNode currentNode = (DecisionNode) nodeFromMap;

        System.out.println("Found Node: " + currentNode);

        Node nextNode = null;
        if ("yes".equalsIgnoreCase(answer)) {
            nextNode = currentNode.getYesBranch();
            System.out.println("Following YES branch to -> " + nodeToString(nextNode));
        } else if ("no".equalsIgnoreCase(answer)) {
            nextNode = currentNode.getNoBranch();
            System.out.println("Following NO branch to -> " + nodeToString(nextNode));
        } else {
            System.err.println("ERROR: Invalid answer received: [" + answer + "]");
            return Optional.empty();
        }

        if (nextNode == null) {
            System.err.println("ERROR: Calculated nextNode is null!");
            return Optional.empty();
        }

        return Optional.of(nextNode);
    }

    private String nodeToString(Node node) {
        if (node instanceof LeafNode) {
            return "Leaf: [" + ((LeafNode) node).getDecision() + "]";
        } else if (node instanceof DecisionNode) {
            DecisionNode dn = (DecisionNode) node;
            return "Decision: [ID: " + dn.getId() + ", Q: " + dn.getQuestion() + "]";
        } else {
            return "Unknown Node Type or Null";
        }
    }

    public Node getRootNode() {
        return rootNode;
    }
}
