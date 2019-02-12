package de.dhbw.inf17a.regextodea;

import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.treenodes.UnaryOpNode;

public interface Visitor
{
    public void visit(OperandNode node);
    public void visit(BinOpNode node);
    public void visit(UnaryOpNode node);
}