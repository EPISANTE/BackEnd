package Episante.back.Models;

import lombok.Getter;
import java.util.Objects;

@Getter
public class LeafNode extends Node {
    private final String decision;

    public LeafNode(String decision) {
        Objects.requireNonNull(decision, "Decision cannot be null");
        if (decision.trim().isEmpty()) {
            throw new IllegalArgumentException("Decision cannot be empty");
        }
        if (!decision.trim().toLowerCase().startsWith("diagnosis:")) {
            this.decision = "Diagnosis: " + decision.trim();
        } else {
            this.decision = decision.trim();
        }
    }

    @Override
    public String toString() {
        return "LeafNode[Decision: '" + decision + "']";
    }
}
