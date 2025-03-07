package Episante.back.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DecisionTreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String text;

    private Long yesNodeId;
    private Long noNodeId;

    public DecisionTreeNode() {}

    public DecisionTreeNode(String type, String text, Long yesNodeId, Long noNodeId) {
        this.type = type;
        this.text = text;
        this.yesNodeId = yesNodeId;
        this.noNodeId = noNodeId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Long getYesNodeId() {
        return yesNodeId;
    }

    public void setYesNodeId(Long yesNodeId) {
        this.yesNodeId = yesNodeId;
    }

    public Long getNoNodeId() {
        return noNodeId;
    }

    public void setNoNodeId(Long noNodeId) {
        this.noNodeId = noNodeId;
    }

    @Override
    public String toString() {
        return "DecisionTreeNode{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", yesNodeId=" + yesNodeId +
                ", noNodeId=" + noNodeId +
                '}';
    }
}
