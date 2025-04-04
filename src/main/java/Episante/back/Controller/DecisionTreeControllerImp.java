package Episante.back.Controller;


import Episante.back.Models.LeafNode;
import Episante.back.Models.Node;
import Episante.back.Service.DecisionTreeServiceImp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/decision")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DecisionTreeControllerImp {

    private final DecisionTreeServiceImp decisionTreeService;

    @GetMapping("/start")
    public ResponseEntity<Node> start() {
        Node root = decisionTreeService.getRootNode();
        if (root != null) {
            return ResponseEntity.ok(root);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }


    @Getter
    @Setter
    public static class AnswerRequest {
        private String question;
        private String answer;
    }

    @PostMapping("/answer")
    public ResponseEntity<Node> answer(@RequestBody AnswerRequest request) {
        if (request == null || request.getQuestion() == null || request.getAnswer() == null) {
            return ResponseEntity.badRequest().body(new LeafNode("Error: Invalid request data."));
        }

        Optional<Node> nextNodeOpt = decisionTreeService.getNextNode(
                request.getQuestion(),
                request.getAnswer()
        );

        return nextNodeOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(new LeafNode("Error: Could not determine next step.")));

    }
}
