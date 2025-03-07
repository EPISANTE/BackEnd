package Episante.back.Repository;

import Episante.back.Models.DecisionTreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecisionTreeNodeRepository extends JpaRepository<DecisionTreeNode, Long> {
    @Query("SELECT d FROM DecisionTreeNode d WHERE d.yesNodeId IS NULL AND d.noNodeId IS NULL AND d.type = 'ANSWER'")
    Iterable<DecisionTreeNode> findAllLeafNodes();

    @Query("SELECT d FROM DecisionTreeNode d WHERE d.text = 'Start'")
    Optional<DecisionTreeNode> findRootNode();

    @Query("SELECT d FROM DecisionTreeNode d WHERE d.id = :nodeId")
    Optional<DecisionTreeNode> findByIdWithChildren(@Param("nodeId") Long nodeId);
}
