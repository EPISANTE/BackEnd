package Episante.back.Service;

import Episante.back.Models.DecisionNode;
import Episante.back.Models.Node ;
import Episante.back.Models.LeafNode ;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Getter
public class DecisionTreeServiceImp {

    private Node rootNode;

    private final Map<String, DecisionNode> decisionNodeMap = new HashMap<>();

    @PostConstruct
    public void buildTree() {

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


        DecisionNode node6aContact = createDecisionNode("Have you been in contact with someone who tested positive for COVID-19?", leafHighlyLikelyCovid, leafPossibleCovid);
        DecisionNode node6bContact = createDecisionNode("Have you been in contact with someone who tested positive for COVID-19?", leafPossibleCovid, leafLikelyCovidMilder);
        DecisionNode node6cSuddenOnset = createDecisionNode("Did your symptoms start suddenly?", leafPossibleFlu, leafPossibleOtherViral);
        DecisionNode node6dHeadache = createDecisionNode("Do you have a headache?", leafPossibleMildFluOrOther, leafPossibleOtherCondition);
        DecisionNode node6eChills = createDecisionNode("Do you have chills?", leafHighlyLikelyFlu, leafPossibleFlu);
        DecisionNode node6fChills = createDecisionNode("Do you have chills?", leafPossibleFlu, leafPossibleMildFluOrOther);
        DecisionNode node6gDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleMildFluOrOther);
        DecisionNode node6hDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6iSuddenOnset = createDecisionNode("Did your symptoms start suddenly?", leafPossibleFlu, leafPossibleOtherViral);
        DecisionNode node6jSuddenOnset = createDecisionNode("Did your symptoms start suddenly?", leafPossibleMildFlu, leafPossibleOtherCondition);
        DecisionNode node6kDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleMildFluOrOther);
        DecisionNode node6lDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6mDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafPossibleCommonCold);
        DecisionNode node6nDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildViral);
        DecisionNode node6oHeadache = createDecisionNode("Do you have a headache?", leafPossibleOtherViral, leafPossibleMildCondition);
        DecisionNode node6pHeadache = createDecisionNode("Do you have a headache?", leafPossibleMildCondition, leafLikelyNotViral);
        DecisionNode node6qDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafLikelyCommonCold);
        DecisionNode node6rDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafHighlyLikelyCommonCold);
        DecisionNode node6sDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleColdOrMild);
        DecisionNode node6tDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyCommonCold);
        DecisionNode node6uDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafPossibleCommonCold);
        DecisionNode node6vDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedMildCondition, leafLikelyCommonCold);
        DecisionNode node6wDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildViral);
        DecisionNode node6xDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6yDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedViral, leafPossibleMildFluOrOther);
        DecisionNode node6zDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildViral);
        DecisionNode node6aaDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedMildCondition, leafPossibleMildViral);
        DecisionNode node6abDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);
        DecisionNode node6acDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedMildCondition, leafPossibleMildViral);
        DecisionNode node6adDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafPossibleMildCondition);
        DecisionNode node6aeDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleProlongedCold, leafPossibleCommonCold);
        DecisionNode node6afDuration = createDecisionNode("Have your symptoms lasted more than 10 days?", leafPossibleOtherCondition, leafLikelyNotViral);

        DecisionNode node5aFatigue = createDecisionNode("Do you have fatigue?", node6aContact, node6bContact);
        DecisionNode node5bBodyAches = createDecisionNode("Do you have body aches?", node6cSuddenOnset, node6dHeadache);
        DecisionNode node5cSuddenOnset = createDecisionNode("Did your symptoms start suddenly?", node6eChills, node6fChills);
        DecisionNode node5dSoreThroat = createDecisionNode("Do you have a sore throat?", node6gDuration, node6hDuration);
        DecisionNode node5eFatigue = createDecisionNode("Do you have fatigue?", node6iSuddenOnset, node6jSuddenOnset);
        DecisionNode node5fSoreThroat = createDecisionNode("Do you have a sore throat?", node6kDuration, node6lDuration);
        DecisionNode node5gSneezing = createDecisionNode("Do you have sneezing?", node6mDuration, node6nDuration);
        DecisionNode node5hFatigue = createDecisionNode("Do you have fatigue?", node6oHeadache, node6pHeadache);
        DecisionNode node5iFatigue = createDecisionNode("Do you have fatigue?", node6qDuration, node6rDuration);
        DecisionNode node5jHeadache = createDecisionNode("Do you have a headache?", node6sDuration, node6tDuration);
        DecisionNode node5kFatigue = createDecisionNode("Do you have fatigue?", node6uDuration, node6vDuration);
        DecisionNode node5lHeadache = createDecisionNode("Do you have a headache?", node6wDuration, node6xDuration);
        DecisionNode node5mBodyAches = createDecisionNode("Do you have body aches?", node6yDuration, node6zDuration);
        DecisionNode node5nSoreThroat = createDecisionNode("Do you have a sore throat?", node6aaDuration, node6abDuration);
        DecisionNode node5oSoreThroat = createDecisionNode("Do you have a sore throat?", node6acDuration, node6adDuration);
        DecisionNode node5pSneezing = createDecisionNode("Do you have sneezing?", node6aeDuration, node6afDuration);

        DecisionNode node4aLoss = createDecisionNode("Have you experienced loss of taste or smell?", node5aFatigue, node5bBodyAches);
        DecisionNode node4bBodyAches = createDecisionNode("Do you have body aches?", node5cSuddenOnset, node5dSoreThroat);
        DecisionNode node4cHeadache = createDecisionNode("Do you have a headache?", node5eFatigue, node5fSoreThroat);
        DecisionNode node4dSoreThroat = createDecisionNode("Do you have a sore throat?", node5gSneezing, node5hFatigue);
        DecisionNode node4eSneezing = createDecisionNode("Do you have sneezing?", node5iFatigue, node5jHeadache);
        DecisionNode node4fSneezing = createDecisionNode("Do you have sneezing?", node5kFatigue, node5lHeadache);
        DecisionNode node4gHeadache = createDecisionNode("Do you have a headache?", node5mBodyAches, node5nSoreThroat);
        DecisionNode node4hHeadache = createDecisionNode("Do you have a headache?", node5oSoreThroat, node5pSneezing);

        DecisionNode node3aShortnessBreath = createDecisionNode("Do you have shortness of breath?", node4aLoss, node4bBodyAches);
        DecisionNode node3bBodyAches = createDecisionNode("Do you have body aches?", node4cHeadache, node4dSoreThroat);
        DecisionNode node3cSoreThroat = createDecisionNode("Do you have a sore throat?", node4eSneezing, node4fSneezing);
        DecisionNode node3dFatigue = createDecisionNode("Do you have fatigue?", node4gHeadache, node4hHeadache);

        DecisionNode node2aCough = createDecisionNode("Do you have a cough?", node3aShortnessBreath, node3bBodyAches);
        DecisionNode node2bRunnyNose = createDecisionNode("Do you have a runny nose?", node3cSoreThroat, node3dFatigue);

        this.rootNode = createDecisionNode("Do you have a fever?", node2aCough, node2bRunnyNose);

        System.out.println("Decision Tree built successfully. Root: "  /* +  this.rootNode.getQuestion()    */);
    }

    private DecisionNode createDecisionNode(String question, Node yesBranch, Node noBranch) {
        DecisionNode node = new DecisionNode(question, yesBranch, noBranch);
        decisionNodeMap.put(node.getQuestion(), node);
        return node;
    }


    public Optional<Node> getNextNode(String currentQuestion, String answer) {
        DecisionNode currentNode = decisionNodeMap.get(currentQuestion);
        if (currentNode == null) {
            System.err.println("Error: Could not find node for question: " + currentQuestion);
            return Optional.empty();
        }

        if ("yes".equalsIgnoreCase(answer)) {
            return Optional.of(currentNode.getYesBranch());
        } else if ("no".equalsIgnoreCase(answer)) {
            return Optional.of(currentNode.getNoBranch());
        } else {
            System.err.println("Error: Invalid answer received: " + answer);
            return Optional.empty();
        }
    }
}
