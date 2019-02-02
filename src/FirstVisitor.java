public class FirstVisitor implements Visitor
{
    @Override
    public void visit(OperandNode node)
    {
        //TODO:hier das epsilon einfügen
        if ("e".equals(node.symbol))
        {
            node.nullable = true;

            node.firstpos.clear();
            node.lastpos.clear();
        } else
        {
            node.nullable = false;

            node.firstpos.add(node.position);
            node.lastpos.add(node.position);
        }
    }

    @Override
    public void visit(BinOpNode node)
    {
        SyntaxNode leftNode = ((SyntaxNode) node.left);
        SyntaxNode rightNode = ((SyntaxNode) node.right);

        switch (node.operator)
        {
            case "|":
                node.nullable = leftNode.nullable || rightNode.nullable;

                node.firstpos.addAll(leftNode.firstpos);
                node.firstpos.addAll(rightNode.firstpos);

                node.lastpos.addAll(leftNode.lastpos);
                node.lastpos.addAll(rightNode.lastpos);

                break;

            case "∘":
                node.nullable = leftNode.nullable && rightNode.nullable;

                node.firstpos.addAll(leftNode.firstpos);
                if (leftNode.nullable)
                    node.firstpos.addAll(rightNode.firstpos);

                if(rightNode.nullable)node.lastpos.addAll(leftNode.lastpos);
                node.lastpos.addAll(rightNode.lastpos);

                break;
            default:
                System.out.println("Sth unexpected Happened: " + node.getClass().toGenericString() + " " + node.operator);
        }
    }

    @Override
    public void visit(UnaryOpNode node)
    {
        SyntaxNode subNode = ((SyntaxNode) node.subNode);
        switch (node.operator)
        {
            case "*":
                node.nullable = true;

                node.firstpos.addAll(subNode.firstpos);
                node.lastpos.addAll(subNode.lastpos);
                break;
            case "?":
                //TODO
                break;
            case "+":
                //TODO
                break;
            default:
                System.out.println("Sth unexpected Happened: " + node.getClass().toGenericString() + " " + node.operator);
                break;
        }

    }
}
