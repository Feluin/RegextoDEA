


public class TopDownParser implements ITopDownParser {

    private String regEx;

    @Override
    public Visitable parse(String regEx) {
        this.regEx = regEx;
        return start();
    }

    private Visitable start() {
        if (regEx.charAt(0) == '#') {
            //#
            return new OperandNode("#");
        } else if (regEx.charAt(0) == '(') {
            //(regexp)#
            OperandNode leaf = new OperandNode("#");
            BinOpNode root = new BinOpNode("°",regExp(1), leaf);
            return root;
        }
        return null;
    }

    private Visitable regExp(int pos) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //termRE
            return (re(term(null, pos), pos));
        }

        return null;
    }

    private Visitable term(Visitable p, int pos) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //factor term
            Visitable term1Parameter = null;
            if (p != null) {
                BinOpNode root = new BinOpNode("°", p, factor(null, pos + 1));
                term1Parameter = root;
            } else {
                term1Parameter = factor(null, pos );
            }
            return term(term1Parameter, pos );
        } else if (c == '|' || c == ')') {
            //epsilon
            return p;
        }

        return null;
    }

    private Visitable re(Visitable p, int pos) {

        if (regEx.charAt(pos) == '|') {
            //|termRE
            BinOpNode root = new BinOpNode("|", p, term(null, pos + 1));
            return re(root, pos + 1);
        } else if (regEx.charAt(pos) == ')') {
            //epsilon
            return p;
        }

        return null;
    }

    private Visitable factor(Visitable p, int pos) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //elem hop
            return hop(elem(null, pos ), pos );
        }

        return null;
    }

    private Visitable hop(Visitable p, int pos) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(' || c == '|' || c == ')') {
            //epsilon
            return hop(p, pos + 1);
        } else if (c == '*') {
            //sternchen
            return (new UnaryOpNode("*", p));
        } else if (c == '+') {
            //plus
            return new UnaryOpNode("+", p);
        } else if (c == '?') {
            //?
            return new UnaryOpNode("?", p);
        }
        return null;
    }

    private Visitable elem(Visitable p, int pos) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //alphanum
            return alphanum(null, pos + 1);
        } else if (c == '(') {
            //klammer regexp klammer
            regExp(pos + 1);
        }
        return null;
    }

    private Visitable alphanum(Visitable p, int pos) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //...
            return new OperandNode("A");
        }
        return null;
    }

}