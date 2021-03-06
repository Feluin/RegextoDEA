package de.dhbw.inf17a.regextodea.visitors;

import de.dhbw.inf17a.regextodea.treenodes.BinOpNode;
import de.dhbw.inf17a.regextodea.treenodes.OperandNode;
import de.dhbw.inf17a.regextodea.treenodes.SyntaxNode;
import de.dhbw.inf17a.regextodea.treenodes.UnaryOpNode;
import de.dhbw.inf17a.regextodea.Visitor;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: Julian Weber
 */


public class SecondVisitor implements Visitor
{

    private SortedMap<Integer, FollowposTableEntry> followposTableEntries = new TreeMap<>();

    public SortedMap<Integer, FollowposTableEntry> getFollowposTableEntries() {
        return followposTableEntries;
    }


    @Override
    public void visit(OperandNode node) {
        FollowposTableEntry entry = new FollowposTableEntry(node.position, node.symbol);
        followposTableEntries.put(node.position, entry);

    }

    @Override
    public void visit(BinOpNode node) {
        switch (node.operator) {
            case "∘":
                for (int i : ((SyntaxNode)node.left).lastpos) {
                    for (int j : ((SyntaxNode) node.right).firstpos) {
                        followposTableEntries.get(i).followpos.add(j);
                    }
                }

                break;

            case "|":
                break;
            default:
                System.out.println("Sth unexpected Happened: " + node.getClass().toGenericString() + " " + node.operator);
                break;


        }
    }

    @Override
    public void visit(UnaryOpNode node) {
        switch (node.operator) {
            case "*":
                for (int i : node.lastpos) {
                    for (int j : node.firstpos) {
                        followposTableEntries.get(i).followpos.add(j);
                    }
                }

                break;

            case "+":
                for (int i : node.lastpos) {
                    for (int j : node.firstpos) {
                        followposTableEntries.get(i).followpos.add(j);
                    }
                }

                break;

            case "?":
                break;

            default:
                System.out.println("Sth unexpected Happened: " + node.getClass().toGenericString() + " " + node.operator);
                break;
        }
    }
}
