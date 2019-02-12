import java.util.Objects;

public class BinOpNode extends SyntaxNode implements Visitable
{
    public String operator;
    public Visitable left;
    public Visitable right;
    public BinOpNode(String operator, Visitable left, Visitable right)
    {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
    @Override
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Visitable obj) {
        if(obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            BinOpNode otherObj = (BinOpNode) obj;
            return (Objects.equals(this.operator, otherObj.operator))
                    && Objects.equals(this.left, otherObj.left)
                    && Objects.equals(this.right, otherObj.right);
        }
    }
}

