package de.dhbw.inf17a.regextodea.treenodes;

import de.dhbw.inf17a.regextodea.Visitable;
import de.dhbw.inf17a.regextodea.Visitor;

import java.util.Objects;

public class OperandNode extends SyntaxNode implements Visitable
{
    public int position;
    public String symbol;
    public OperandNode(String symbol)
    {
        position = -1; // bedeutet: noch nicht initialisiert
        this.symbol = symbol;
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
            OperandNode otherObj = (OperandNode) obj;
            return Objects.equals(this.symbol, otherObj.symbol);
        }
    }
}
