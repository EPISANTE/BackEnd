package Episante.back;

import Episante.back.Service.DecisionTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DecisionTreeService decisionTreeService;

    @Override
    public void run(String... args) throws Exception {
        decisionTreeService.createInitialTree();
    }
}