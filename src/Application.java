import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Visitable visitable = null;

        ITopDownParser parser = new TopDownParser();
        Visitor visitorFirst = new FirstVisitor();
        Visitor visitorSecond = new SecondVisitor();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Eingabe: ");
        String regEx = scanner.next();

        visitable = parser.parse(regEx);

        if(visitable == null) {
            System.out.println("Ausdurck nicht geparst!");
        }

        visitable.accept(visitorFirst);
        visitable.accept(visitorSecond);
    }
}
