package Episante.back.Controller;

import Episante.back.Models.DecisionTreeNode;
import Episante.back.Service.DecisionTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/decision-tree")

public class DecisionTreeController {

    @Autowired
    private DecisionTreeService decisionTreeService;


    @GetMapping("/start")
    public ResponseEntity<DecisionTreeNode> startTree() {
        DecisionTreeNode root = decisionTreeService.getRootNode();
        if (root != null) {
            return ResponseEntity.ok(root);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/next")
    public ResponseEntity<DecisionTreeNode> getNextNode(@RequestParam Long currentNodeId, @RequestParam String answer) {
        DecisionTreeNode nextNode = decisionTreeService.getNextNode(currentNodeId, answer);
        if (nextNode != null) {
            return ResponseEntity.ok(nextNode);
        } else {

            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/initial-tree")
    public ResponseEntity<Map<Long, DecisionTreeNode>> getInitialTree()
    {
        Map<Long, DecisionTreeNode> initialTree = decisionTreeService.getInitialTree();
        return ResponseEntity.ok(initialTree);
    }

    @PostMapping("/load-initial-data")
    public ResponseEntity<?> loadInitialData() {
        decisionTreeService.createInitialTree();
        return ResponseEntity.ok("Initial tree data loaded.");
    }
}