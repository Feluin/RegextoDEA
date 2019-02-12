import java.util.Objects;

public class UnaryOpNode extends SyntaxNode implements Visitable
{
    public String operator;
    public Visitable subNode;
    public UnaryOpNode(String operator, Visitable subNode)
    {
        this.operator = operator;
        this.subNode = subNode;
    }
    @Override
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Visitable obj) {
        if(obj == null ||!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            UnaryOpNode otherUnOp = (UnaryOpNode) obj;
            return Objects.equals(this.operator, otherUnOp.operator)
                    && Objects.equals(this.subNode, otherUnOp.subNode);
        }
    }
}
