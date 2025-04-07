package Episante.back.Service;

import Episante.back.Models.DecisionNode;
import Episante.back.Models.LeafNode;
import Episante.back.Models.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DecisionTreeServiceImpTest {

    private DecisionTreeServiceImp decisionTreeService;

    @BeforeEach
    void setUp() {
        decisionTreeService = new DecisionTreeServiceImp();
        decisionTreeService.buildTree();
    }

    @Test
    @DisplayName("buildTree should initialize a non-null root node")
    void testBuildTree_InitializesRootNode() {
        Node rootNode = decisionTreeService.getRootNode();

        assertThat(rootNode).isNotNull();
        assertThat(rootNode).isInstanceOf(DecisionNode.class);

        assertThat(((DecisionNode) rootNode).getQuestion())
                .isEqualToIgnoringCase("Do you have a fever? (yes/no)");
    }

    @Test
    @DisplayName("buildTree should populate the node map")
    void testBuildTree_PopulatesNodeMap() {



        assertThat(decisionTreeService.getNodeMap()).isNotNull();
        assertThat(decisionTreeService.getNodeMap().isEmpty()).isFalse();

        Node root = decisionTreeService.getRootNode();
        if (root instanceof DecisionNode) {
            assertThat(decisionTreeService.getNodeMap()).containsKey(((DecisionNode) root).getId());
        }
    }

    @Test
    @DisplayName("getRootNode should return the initialized root node")
    void testGetRootNode_ReturnsCorrectNode() {

        Node rootNode = decisionTreeService.getRootNode();


        assertThat(rootNode).isNotNull();
        assertThat(rootNode).isInstanceOf(DecisionNode.class);
    }

    @Test
    @DisplayName("getNextNode should return correct 'yes' branch for valid ID")
    void testGetNextNode_ValidIdYesAnswer_ReturnsYesBranch() {

        Node root = decisionTreeService.getRootNode();
        assertThat(root).isInstanceOf(DecisionNode.class);
        DecisionNode rootDecisionNode = (DecisionNode) root;
        String rootId = rootDecisionNode.getId();
        Node expectedNextNode = rootDecisionNode.getYesBranch();


        Optional<Node> actualNextNodeOpt = decisionTreeService.getNextNode(rootId, "yes");


        assertThat(actualNextNodeOpt).isPresent();
        assertThat(actualNextNodeOpt.get()).isNotNull();

        assertThat(actualNextNodeOpt.get()).isEqualTo(expectedNextNode);

        assertThat(actualNextNodeOpt.get()).isInstanceOf(expectedNextNode.getClass());
    }

    @Test
    @DisplayName("getNextNode should return correct 'no' branch for valid ID")
    void testGetNextNode_ValidIdNoAnswer_ReturnsNoBranch() {

        Node root = decisionTreeService.getRootNode();
        assertThat(root).isInstanceOf(DecisionNode.class);
        DecisionNode rootDecisionNode = (DecisionNode) root;
        String rootId = rootDecisionNode.getId();
        Node expectedNextNode = rootDecisionNode.getNoBranch();


        Optional<Node> actualNextNodeOpt = decisionTreeService.getNextNode(rootId, "no");


        assertThat(actualNextNodeOpt).isPresent();
        assertThat(actualNextNodeOpt.get()).isNotNull();
        assertThat(actualNextNodeOpt.get()).isEqualTo(expectedNextNode);
        assertThat(actualNextNodeOpt.get()).isInstanceOf(expectedNextNode.getClass());
    }

    @Test
    @DisplayName("getNextNode should handle case-insensitive answers")
    void testGetNextNode_CaseInsensitiveAnswer() {

        Node root = decisionTreeService.getRootNode();
        assertThat(root).isInstanceOf(DecisionNode.class);
        DecisionNode rootDecisionNode = (DecisionNode) root;
        String rootId = rootDecisionNode.getId();
        Node expectedYesNode = rootDecisionNode.getYesBranch();
        Node expectedNoNode = rootDecisionNode.getNoBranch();


        Optional<Node> actualYesOpt = decisionTreeService.getNextNode(rootId, "YeS");
        Optional<Node> actualNoOpt = decisionTreeService.getNextNode(rootId, "NO");



        assertThat(actualYesOpt).isPresent();
        assertThat(actualYesOpt.get()).isEqualTo(expectedYesNode);
        assertThat(actualNoOpt).isPresent();
        assertThat(actualNoOpt.get()).isEqualTo(expectedNoNode);
    }

    @Test
    @DisplayName("getNextNode should return empty Optional for invalid node ID")
    void testGetNextNode_InvalidNodeId_ReturnsEmpty() {

        String invalidId = UUID.randomUUID().toString();
        String validAnswer = "yes";


        Optional<Node> resultOpt = decisionTreeService.getNextNode(invalidId, validAnswer);


        assertThat(resultOpt).isNotPresent();
    }

    @Test
    @DisplayName("getNextNode should return empty Optional for invalid answer")
    void testGetNextNode_InvalidAnswer_ReturnsEmpty() {

        Node root = decisionTreeService.getRootNode();
        assertThat(root).isInstanceOf(DecisionNode.class);
        String validId = ((DecisionNode) root).getId();
        String invalidAnswer = "maybe";


        Optional<Node> resultOpt = decisionTreeService.getNextNode(validId, invalidAnswer);


        assertThat(resultOpt).isNotPresent();
    }

    @Test
    @DisplayName("getNextNode should correctly return a LeafNode when path ends")
    void testGetNextNode_PathLeadsToLeafNode() {

        Optional<DecisionNode> node6aOpt = decisionTreeService.getNodeMap().values().stream()
                .filter(n -> n instanceof DecisionNode)
                .map(n -> (DecisionNode) n)
                .filter(dn -> dn.getQuestion().toLowerCase().contains("contact with someone who tested positive"))
                .filter(dn -> dn.getYesBranch() instanceof LeafNode && ((LeafNode)dn.getYesBranch()).getDecision().contains("Highly likely"))
                .findFirst();

        assertThat(node6aOpt).as("Test setup failed: Could not find node6aContact node instance").isPresent();
        DecisionNode node6aContact = node6aOpt.get();
        String nodeId = node6aContact.getId();
        Node expectedLeaf = node6aContact.getNoBranch();


        Optional<Node> resultOpt = decisionTreeService.getNextNode(nodeId, "no");


        assertThat(resultOpt).isPresent();
        assertThat(resultOpt.get()).isInstanceOf(LeafNode.class);
        assertThat(resultOpt.get()).isEqualTo(expectedLeaf);
        assertThat(((LeafNode) resultOpt.get()).getDecision()).isEqualTo("Diagnosis: Possible COVID-19");
    }

}
