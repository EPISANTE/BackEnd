package Episante.back.test.decisionTree;

import java.util.Objects;

public class DecisionNode extends Node{
    final String question ;
    final Node yesBranch ;
    final Node noBranch ;

    public DecisionNode(String question , Node yesBranch , Node noBranch){
        Objects.requireNonNull(question , "The Question Should be not null") ;
        Objects.requireNonNull(yesBranch , "yesBranch cannot be null");
        Objects.requireNonNull(yesBranch , "noBranch cannot be null");

        if (question.trim().isEmpty()){
            throw new IllegalArgumentException("The question cannot be empty");
        }
        this.question = question;
        this.yesBranch = yesBranch ;
        this.noBranch = noBranch ;
    }

    public String getQuestion() {
        return question;
    }

    public Node getYesBranch() {
        return yesBranch;
    }

    public Node getNoBranch() {
        return noBranch;
    }

    @Override
    public String toString() {
        return "DecisionNode[Q: '" + question + "']";
    }
}
