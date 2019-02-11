import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashSet;
import java.util.Set;

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


    void positionTest()
    {
        //Für Raphael //TODO
        Assertions.assertEquals(1, k1.position);
        Assertions.assertEquals( 2,k2.position);
        Assertions.assertEquals( 3,k5.position);
        Assertions.assertEquals(4,k7.position);
        Assertions.assertEquals( 5,k9.position);
        Assertions.assertEquals(6,k11.position);
    }

    @Test
    void nullableTest()
    {
        Assertions.assertEquals( false,k1.nullable);
        Assertions.assertEquals( false,k2.nullable);
        Assertions.assertEquals( false,k3.nullable);
        Assertions.assertEquals( true,k4.nullable);
        Assertions.assertEquals( false,k5.nullable);
        Assertions.assertEquals( false,k6.nullable);
        Assertions.assertEquals( false,k7.nullable);
        Assertions.assertEquals( false,k8.nullable);
        Assertions.assertEquals( false,k9.nullable);
        Assertions.assertEquals( false,k10.nullable);
        Assertions.assertEquals( false,k11.nullable);
        Assertions.assertEquals( false,rootNode.nullable);

    }
    @Test
    void firstPosTest()
    {
        Assertions.assertEquals(Set.of(1),k1.firstpos);
        Assertions.assertEquals(Set.of(2),k2.firstpos);
        Assertions.assertEquals(Set.of(1,2),k3.firstpos);
        Assertions.assertEquals(Set.of(1,2),k4.firstpos);
        Assertions.assertEquals(Set.of(3),k5.firstpos);
        Assertions.assertEquals(Set.of(1,2,3),k6.firstpos);
        Assertions.assertEquals(Set.of(4),k7.firstpos);
        Assertions.assertEquals(Set.of(1,2,3),k8.firstpos);
        Assertions.assertEquals(Set.of(5),k9.firstpos);
        Assertions.assertEquals(Set.of(1,2,3),k10.firstpos);
        Assertions.assertEquals(Set.of(6),k11.firstpos);
        Assertions.assertEquals(Set.of(1,2,3),rootNode.firstpos);
    }
    @Test
    void lastPosTest()
    {
        Assertions.assertEquals(Set.of(1),k1.lastpos);
        Assertions.assertEquals(Set.of(2),k2.lastpos);
        Assertions.assertEquals(Set.of(1,2),k3.lastpos);
        Assertions.assertEquals(Set.of(1,2),k4.lastpos);
        Assertions.assertEquals(Set.of(3),k5.lastpos);
        Assertions.assertEquals(Set.of(3),k6.lastpos);
        Assertions.assertEquals(Set.of(4),k7.lastpos);
        Assertions.assertEquals(Set.of(4),k8.lastpos);
        Assertions.assertEquals(Set.of(5),k9.lastpos);
        Assertions.assertEquals(Set.of(5),k10.lastpos);
        Assertions.assertEquals(Set.of(6),k11.lastpos);
        Assertions.assertEquals(Set.of(6),rootNode.lastpos);

    }

}
