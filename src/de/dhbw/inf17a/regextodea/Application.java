package de.dhbw.inf17a.regextodea;

import de.dhbw.inf17a.regextodea.parser.TopDownParser;
import de.dhbw.inf17a.regextodea.visitors.FirstVisitor;
import de.dhbw.inf17a.regextodea.visitors.SecondVisitor;

import java.util.Scanner;

public class Application
{

    public static void main(String[] args)
    {
        Visitable visitable = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Eingabe: ");
        String regEx = scanner.next();

        TopDownParser parser = new TopDownParser(regEx);

        visitable = parser.getTreeFromRegex();

        if (visitable == null)
        {
            System.out.println("Failure parsing!");
        }

        DepthFirstIterator.traverse(visitable, new FirstVisitor());
        DepthFirstIterator.traverse(visitable, new SecondVisitor());
    }
}
