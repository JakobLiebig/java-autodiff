import autodiff.Node;

public class Main {
    public static void main(String argv[]) {
        Node a = new Node(2.);
        Node b = new Node(3.);

        Node c = a.add(b);
        Node d = a.add(b);

        Node result = c.multiply(d);

        System.out.println(a.getValue() + " + " + b.getValue() + " * " + c.getValue());
        System.out.println(" = " + result.getValue());

        result.calculateGradients();
        System.out.println("drdb = " + b.getGradient());
    }
}