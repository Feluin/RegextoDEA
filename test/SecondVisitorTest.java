import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.DepthFirstIterator;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.visitors.SecondVisitor;
import de.dhbw.inf17a.regextodea.treenodes.UnaryOpNode;
import de.dhbw.inf17a.regextodea.Visitor;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SecondVisitorTest {


    @Test
    public void test1() {

        //1ter Knoten
        OperandNode k1 = new OperandNode("a");
        k1.position = 1;
        k1.firstpos.add(1);
        k1.lastpos.add(1);
        k1.nullable = false;

        //2ter Knoten
        OperandNode k2 = new OperandNode("b");
        k2.position = 2;
        k2.nullable = false;
        k2.firstpos.add(2);
        k2.lastpos.add(2);

        //3ter Knoten
        BinOpNode k3 = new BinOpNode("|", k1, k2);
        k3.firstpos.add(1);
        k3.firstpos.add(2);
        k3.lastpos.add(1);
        k3.lastpos.add(2);
        k3.nullable = false;

        //4ter Knoten
        UnaryOpNode k4 = new UnaryOpNode("*", k3);
        k4.firstpos.add(1);
        k4.firstpos.add(2);
        k4.lastpos.add(1);
        k4.lastpos.add(2);
        k4.nullable = true;

        //5ter Knoten
        OperandNode k5 = new OperandNode("a");
        k5.position = 3;
        k5.nullable = false;
        k5.firstpos.add(3);
        k5.lastpos.add(3);

        //6ter Knoten
        BinOpNode k6 = new BinOpNode("∘", k4, k5);
        k6.lastpos.add(3);
        k6.firstpos.add(1);
        k6.firstpos.add(2);
        k6.firstpos.add(3);
        k6.nullable = false;

        //7ter Knoten
        OperandNode k7 = new OperandNode("b");
        k7.position = 4;
        k7.lastpos.add(4);
        k7.firstpos.add(4);
        k7.nullable = false;

        //8ter Knoten
        BinOpNode k8 = new BinOpNode("∘", k6, k7);
        k8.nullable = false;
        k8.firstpos.add(1);
        k8.firstpos.add(2);
        k8.firstpos.add(3);
        k8.lastpos.add(4);

        //9ter Knoten
        OperandNode k9 = new OperandNode("b");
        k9.nullable = false;
        k9.firstpos.add(5);
        k9.lastpos.add(5);
        k9.position = 5;

        //10ter Knoten
        BinOpNode k10 = new BinOpNode("∘", k8, k9);
        k10.nullable = false;
        k10.firstpos.add(1);
        k10.firstpos.add(2);
        k10.firstpos.add(3);
        k10.lastpos.add(5);

        //11ter Knoten
        OperandNode k11 = new OperandNode("#");
        k11.position = 6;
        k11.firstpos.add(6);
        k11.lastpos.add(6);
        k11.nullable = false;

        //Root
        BinOpNode root = new BinOpNode("∘", k10, k11);
        root.nullable = false;
        root.lastpos.add(6);
        root.firstpos.add(1);
        root.firstpos.add(2);
        root.firstpos.add(3);

        DepthFirstIterator iterator = new DepthFirstIterator();
        Visitor visitor = new SecondVisitor();

        iterator.traverse(root, visitor);


        HashSet<Integer> mySet = new HashSet<>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);


        assertEquals(mySet, ((SecondVisitor) visitor).getFollowposTableEntries().get(1).followpos);
        assertEquals(mySet, ((SecondVisitor) visitor).getFollowposTableEntries().get(2).followpos);
        mySet = new HashSet<>();
        mySet.add(4);
        assertEquals(mySet, ((SecondVisitor) visitor).getFollowposTableEntries().get(3).followpos);
        mySet = new HashSet<>();
        mySet.add(5);
        assertEquals(mySet, ((SecondVisitor) visitor).getFollowposTableEntries().get(4).followpos);
        mySet = new HashSet<>();
        mySet.add(6);
        assertEquals(mySet, ((SecondVisitor) visitor).getFollowposTableEntries().get(5).followpos);
        mySet = new HashSet<>();
        assertEquals(mySet, ((SecondVisitor) visitor).getFollowposTableEntries().get(6).followpos);


    }

}