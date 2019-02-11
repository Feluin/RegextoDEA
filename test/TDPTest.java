import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TDPTest {

    @Test
    void getTreeFromRegex() {
        String regEx = "#";
        Visitable ref = new OperandNode("#");
        assertEquals(ref, new TDP(regEx, true).getTreeFromRegex());
        assertEquals(ref, getTreeFromRegEx(regEx));
    }

    private Visitable getTreeFromRegEx(String regEx) {
        return new TDP(regEx).getTreeFromRegex();
    }
}