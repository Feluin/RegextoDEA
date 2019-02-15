import static org.junit.jupiter.api.Assertions.*;

import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.DepthFirstIterator;
import de.dhbw.inf17a.regextodea.visitors.FirstVisitor;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.treenodes.UnaryOpNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;

/**
 * @author: Pascal Röcker
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstVisitorTest
{
    OperandNode k1;
    OperandNode k2;
    BinOpNode k3;
    UnaryOpNode k4;
    OperandNode k5;
    BinOpNode k6;
    OperandNode k7;
    BinOpNode k8;
    OperandNode k9;
    BinOpNode k10;
    OperandNode k11;
    private BinOpNode rootNode;

    @BeforeAll
    void createTree()
    {
        //1ter Knoten
        k1 = new OperandNode("a");
        k1.position = 1;

        //2ter Knoten
        k2 = new OperandNode("b");
        k2.position = 2;

        //3ter Knoten
        k3 = new BinOpNode("|", k1, k2);

        //4ter Knoten
        k4 = new UnaryOpNode("*", k3);

        //5ter Knoten
        k5 = new OperandNode("a");
        k5.position = 3;

        //6ter Knoten
        k6 = new BinOpNode("∘", k4, k5);

        //7ter Knoten
        k7 = new OperandNode("b");
        k7.position = 4;

        //8ter Knoten
        k8 = new BinOpNode("∘", k6, k7);

        //9ter Knoten
        k9 = new OperandNode("b");
        k9.position = 5;

        //10ter Knoten
        k10 = new BinOpNode("∘", k8, k9);

        //11ter Knoten
        k11 = new OperandNode("#");
        k11.position = 6;
        //Root
        rootNode = new BinOpNode("∘", k10, k11);

        //start
        DepthFirstIterator.traverse(rootNode, new FirstVisitor());
    }
    @Test
    void nullableTest()
    {
        assertEquals(false, k1.nullable);
        assertEquals(false, k2.nullable);
        assertEquals(false, k3.nullable);
        assertEquals(true, k4.nullable);
        assertEquals(false, k5.nullable);
        assertEquals(false, k6.nullable);
        assertEquals(false, k7.nullable);
        assertEquals(false, k8.nullable);
        assertEquals(false, k9.nullable);
        assertEquals(false, k10.nullable);
        assertEquals(false, k11.nullable);
        assertEquals(false, rootNode.nullable);

    }

    @Test
    void firstPosTest()
    {
        assertEquals(Set.of(1), k1.firstpos);
        assertEquals(Set.of(2), k2.firstpos);
        assertEquals(Set.of(1, 2), k3.firstpos);
        assertEquals(Set.of(1, 2), k4.firstpos);
        assertEquals(Set.of(3), k5.firstpos);
        assertEquals(Set.of(1, 2, 3), k6.firstpos);
        assertEquals(Set.of(4), k7.firstpos);
        assertEquals(Set.of(1, 2, 3), k8.firstpos);
        assertEquals(Set.of(5), k9.firstpos);
        assertEquals(Set.of(1, 2, 3), k10.firstpos);
        assertEquals(Set.of(6), k11.firstpos);
        assertEquals(Set.of(1, 2, 3), rootNode.firstpos);
    }

    @Test
    void lastPosTest()
    {
        assertEquals(Set.of(1), k1.lastpos);
        assertEquals(Set.of(2), k2.lastpos);
        assertEquals(Set.of(1, 2), k3.lastpos);
        assertEquals(Set.of(1, 2), k4.lastpos);
        assertEquals(Set.of(3), k5.lastpos);
        assertEquals(Set.of(3), k6.lastpos);
        assertEquals(Set.of(4), k7.lastpos);
        assertEquals(Set.of(4), k8.lastpos);
        assertEquals(Set.of(5), k9.lastpos);
        assertEquals(Set.of(5), k10.lastpos);
        assertEquals(Set.of(6), k11.lastpos);
        assertEquals(Set.of(6), rootNode.lastpos);

    }

}
