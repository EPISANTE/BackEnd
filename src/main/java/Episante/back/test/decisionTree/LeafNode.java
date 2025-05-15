package Episante.back.test.decisionTree;

import java.util.Objects;

public class LeafNode extends Node{
    final String decision;// final decision

    public LeafNode (String decision){
        Objects.requireNonNull(decision , "Decision should be not null");
        if (decision.trim().isEmpty()){
                throw new IllegalArgumentException("Decision should be not null");
        }
        this.decision = decision ;
    }

    public String getDecision(){
        return decision ;
    }

    @Override
    public String toString() {
        return "LeafNode[Decision: '" + decision + "']";
    }
}
