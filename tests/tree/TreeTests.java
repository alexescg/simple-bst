package tree;

import alexescg.com.github.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author alex
 */
public class TreeTests {

    private Tree<Integer, String> tree;

    @BeforeEach
    public void setup() {
        tree = new Tree<>();
        tree.put(10, "diez");

        tree.put(5, "cinco");
        tree.put(6, "seis");
        tree.put(3, "tres");
        tree.put(1, "uno");

        tree.put(20, "veinte");
        tree.put(15, "quince");
        tree.put(30, "treinta");
        tree.put(17, "diecisiete");
    }

    @Test
    public void isEmptyTreeTest(){
        Assertions.assertTrue(!tree.isEmpty(), "Tree has one node");
    }

    @Test
    public void treePutTest() {
        tree.put(1, "uno");
        Assertions.assertTrue(!tree.isEmpty(), "Tree has one node");
    }

    @Test
    public void treeCountTest(){
        tree.put(1, "uno");
        tree.put(1, "uno");
        tree.put(2, "dos");
        tree.put(2, "dos");
        tree.put(2, "dos");
        tree.put(6, "seis");
        Assertions.assertTrue(tree.count(10) == 1,"Tree counts 1 correctly");
        Assertions.assertTrue(tree.count(1) == 3,"Tree counts 3 correctly with node previously inserted");
        Assertions.assertTrue(tree.count(2) == 3,"Tree counts correctly with new nodes");
    }

    @Test
    public void treeLevelTest(){
        Assertions.assertTrue(tree.findLevel(10) == 1, "First Level");
        Assertions.assertTrue(tree.findLevel(5) == 2, "Second Level");
        Assertions.assertTrue(tree.findLevel(17) == 4, "Fourth Level");
    }

    @Test
    public void treeNodeNotFoundLevelTest(){
        Assertions.assertTrue(tree.findLevel(50) == -1, "First Level");
    }

    @Test
    public void noNodesLevelTest(){
        Tree<Integer, String> emptyTree = new Tree<>();
        Assertions.assertTrue(emptyTree.findLevel(1) == -1, "First Level");
    }

    @Test
    public void deleteLeafTest(){
        tree.delete(17);
        Assertions.assertTrue(tree.get(17) == null, "Delete Leaf node");
    }

    @Test
    public void deleteNodeOneLeafTest(){
        tree.delete(3);
        Assertions.assertTrue(tree.get(3) == null, "Tree successfully deleted");
        Assertions.assertTrue(tree.get(1).equals("uno"), "Tree did not delete leaf");
    }

    @Test
    public void deleteNodeTwoLeafTest(){
        tree.delete(20);
        Assertions.assertTrue(tree.get(15).equals("quince"), "Tree did not delete left node");
        Assertions.assertTrue(tree.get(30).equals("treinta"), "Tree did not delete right node");
        Assertions.assertTrue(tree.get(17).equals("diecisiete"), "Tree did not delete child of child node");
    }

}
