import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopDownParserTest {

    @Test
    void parse() {
        Visitable visitable = null;
        ITopDownParser parser = new TopDownParser();
        String regEx = "(a|b)";

        assertNotNull(parser.parse(regEx));
    }
    @Test
    void parse2() {
        Visitable visitable = null;
        ITopDownParser parser = new TopDownParser();
        String regEx= "(a|b)*ab";
        assertNotNull(parser.parse(regEx));
    }
}