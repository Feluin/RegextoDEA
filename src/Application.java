import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Visitable visitable = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Eingabe: ");
        String regEx = scanner.next();

        TDP parser = new TDP(regEx);
        Visitor visitorFirst = new FirstVisitor();
        Visitor visitorSecond = new SecondVisitor();

       visitable = parser.getTreeFromRegex();

       if(visitable == null) {
           System.out.println("Failure parsing!");
       }

        visitable.accept(visitorFirst);
        visitable.accept(visitorSecond);
    }
}
