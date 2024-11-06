package org.example.autodiff.branches;

import java.util.LinkedList;

import org.example.autodiff.Branch;
import org.example.autodiff.Node;

public class Add extends Branch {
    private Node sum;

    public Add(Node first_parent, Node second_parent, Node sum) {
        this.parents = new Node[]{first_parent, second_parent};
        this.sum = sum;
    }

    @Override
    public void back(LinkedList<Branch> queue) {
        Node first_parent = this.parents[0];
        Node second_parent = this.parents[1];

        double first_gradient = sum.getGradient();
        double second_gradient = sum.getGradient();

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
