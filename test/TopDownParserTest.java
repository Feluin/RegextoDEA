import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.parser.TopDownParser;
import de.dhbw.inf17a.regextodea.Visitable;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TopDownParserTest {

    @Test
    void getTreeFromRegexTest1() {
        String regEx = "#";
        Visitable ref = new OperandNode("#");
        assertEquals(ref, new TopDownParser(regEx, true).getTreeFromRegex());
        assertEquals(ref, getTreeFromRegEx(regEx));
    }

    @Test
    void getTreeFromRegexTest2() {
        String regEx = "(A|B)#";
        Visitable ref = new OperandNode("#");
        OperandNode aNode = new OperandNode("A");
        OperandNode bNode = new OperandNode("B");
        ref = new BinOpNode("°", null, ref);
        ((BinOpNode)ref).left = new BinOpNode("|", aNode, bNode);
        assertEquals(ref, getTreeFromRegEx(regEx));
    }

    @Test
    void getTreeFromRegexTest3() {
        String regEx = "(AB1|2)";
        Visitable ref = new OperandNode("#");
        OperandNode aNode = new OperandNode("A");
        OperandNode bNode = new OperandNode("B");
        OperandNode node1 = new OperandNode("1");
        OperandNode node2 = new OperandNode("2");
        ref = new BinOpNode("°", null, ref);
        ((BinOpNode)ref).left = new BinOpNode("°", new BinOpNode("°", aNode, bNode), node1);
        ((BinOpNode)ref).left = new BinOpNode("|",((BinOpNode)ref).left, node2);
        assertEquals(ref, getTreeFromRegEx(regEx));

    }

    private Visitable getTreeFromRegEx(String regEx) {
        return new TopDownParser(regEx).getTreeFromRegex();
    }
}