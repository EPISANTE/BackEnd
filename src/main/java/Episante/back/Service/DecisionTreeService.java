package Episante.back.Service;

import Episante.back.DecisionTreeBuilder;
import Episante.back.Models.DecisionTreeNode;
import Episante.back.Repository.DecisionTreeNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DecisionTreeService {

    @Autowired
    private DecisionTreeNodeRepository decisionTreeNodeRepository;


    @Transactional(readOnly = true)
    public DecisionTreeNode getRootNode() {
        return decisionTreeNodeRepository.findRootNode().orElse(null);
    }


    @Transactional(readOnly = true)
    public DecisionTreeNode getNodeById(Long nodeId) {
        return decisionTreeNodeRepository.findById(nodeId).orElse(null);
    }
    @Transactional(readOnly = true)
    public Map<Long, DecisionTreeNode> getInitialTree() {

        DecisionTreeNode rootNode = decisionTreeNodeRepository.findRootNode()
                .orElseThrow(() -> new RuntimeException("Root node not found"));

        Map<Long, DecisionTreeNode> treeMap = new HashMap<>();

        populateTreeMap(rootNode, treeMap);
        return treeMap;
    }

    private void populateTreeMap(DecisionTreeNode node, Map<Long, DecisionTreeNode> treeMap) {
        if(node == null || treeMap.containsKey(node.getId())){
            return;
        }

        treeMap.put(node.getId(), node);


        if(node.getYesNodeId() != null) {
            Optional<DecisionTreeNode> yesNode = decisionTreeNodeRepository.findById(node.getYesNodeId());
            yesNode.ifPresent(decisionTreeNode -> populateTreeMap(decisionTreeNode,treeMap));
        }
        if (node.getNoNodeId() != null){
            Optional<DecisionTreeNode> noNode = decisionTreeNodeRepository.findById(node.getNoNodeId());
            noNode.ifPresent(decisionTreeNode -> populateTreeMap(decisionTreeNode,treeMap));
        }
    }

    @Transactional
    public DecisionTreeNode saveNode(DecisionTreeNode node) {
        return decisionTreeNodeRepository.save(node);
    }

    @Transactional
    public void createInitialTree() {
        //Check if the tree is already loaded:
        if(getRootNode() != null){
            return;
        }
        Map<Long, DecisionTreeNode> initialTree = DecisionTreeBuilder.buildIllnessTree();


        initialTree.values().forEach(node -> decisionTreeNodeRepository.save(node));
    }

    public DecisionTreeNode getNextNode(Long currentNodeId, String answer) {
        DecisionTreeNode currentNode = decisionTreeNodeRepository.findById(currentNodeId).orElse(null);
        if (currentNode == null || "ANSWER".equals(currentNode.getType())) {
            return null;
        }

        Long nextNodeId = "yes".equalsIgnoreCase(answer) ? currentNode.getYesNodeId() : currentNode.getNoNodeId();
        if (nextNodeId == null) {
            return null;
        }
        return decisionTreeNodeRepository.findById(nextNodeId).orElse(null);
    }
}
