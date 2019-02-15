package de.dhbw.inf17a.regextodea;

import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.treenodes.UnaryOpNode;

public interface Visitor
{
    void visit(OperandNode node);
    void visit(BinOpNode node);
    void visit(UnaryOpNode node);
}