import java.util.Scanner;

public class Application
{

    public static void main(String[] args)
    {
        Visitable visitable = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Eingabe: ");
        String regEx = scanner.next();

        TDP parser = new TDP(regEx);

        visitable = parser.getTreeFromRegex();

        if (visitable == null)
        {
            System.out.println("Failure parsing!");
        }

        DepthFirstIterator.traverse(visitable, new FirstVisitor());
        DepthFirstIterator.traverse(visitable, new SecondVisitor());
    }
}
