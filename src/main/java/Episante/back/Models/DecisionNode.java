package Episante.back.Models;

import lombok.Getter;
import java.util.Objects;

@Getter
public class DecisionNode extends Node {
    @Getter
    private final String question;
    private final Node yesBranch;
    private final Node noBranch;

    public DecisionNode(String question, Node yesBranch, Node noBranch) {
        Objects.requireNonNull(question, "Question cannot be null");
        Objects.requireNonNull(yesBranch, "Yes branch cannot be null");
        Objects.requireNonNull(noBranch, "No branch cannot be null");
        if (question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        if (!question.trim().toLowerCase().endsWith("(yes/no)")) {
            this.question = question.trim() + " (yes/no)";
        } else {
            this.question = question.trim();
        }
        this.yesBranch = yesBranch;
        this.noBranch = noBranch;
    }


    @Override
    public String toString() {
        return "DecisionNode[Q: '" + question + "']";
    }
}
