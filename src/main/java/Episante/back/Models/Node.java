package Episante.back.Models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DecisionNode.class, name = "decision"),
        @JsonSubTypes.Type(value = LeafNode.class, name = "leaf")
})
public abstract class Node {

}