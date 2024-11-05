package autodiff.branches;

import java.util.LinkedList;

import autodiff.Branch;
import autodiff.Node;


public class Multiply extends Branch {
    private Node produkt;

    public Multiply(Node first_parent, Node second_parent, Node produkt) {
        this.parents = new Node[]{first_parent, second_parent};
        this.produkt = produkt;
    }    

    @Override
    public void back(LinkedList<Branch> queue) {
        Node first_parent = this.parents[0];
        Node second_parent = this.parents[1];

        double first_gradient = produkt.getGradient() * second_parent.getValue();
        double second_gradient = produkt.getGradient() * first_parent.getValue();

        first_parent.addToGradient(first_gradient);
        second_parent.addToGradient(second_gradient);

        Branch first_branch = first_parent.getBranch();
        Branch second_branch = second_parent.getBranch();

        if (first_branch != null)
            queue.add(first_branch);
        if (second_branch != null)
            queue.add(second_branch);
    }
}
