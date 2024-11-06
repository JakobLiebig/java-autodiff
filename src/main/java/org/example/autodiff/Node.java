package org.example.autodiff;

import java.util.LinkedList;

import org.example.autodiff.branches.Multiply;
import org.example.autodiff.branches.Add;

public class Node {
    private final double value;
    private double gradient;

    private Branch branch;

    public Node(double val) {
        // leaf
        this.value = val;
        this.gradient = 0.;

        this.branch = null;
    }
    public Node(double val, Branch branch) {
        this.value = val;
        this.gradient = 0.;

        this.branch = branch;
    }

    public double getValue() {
        return value;
    }
    public double getGradient() {
        return gradient;
    }
    public void addToGradient(double gradient) {
        this.gradient += gradient;
    }
    public void setGradient(double gradient) {
        this.gradient = gradient;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Node multiply(Node other) {
        double product_val = this.value * other.value;
        Node product = new Node(product_val);

        Branch product_branch = new Multiply(this, other, product);

        product.setBranch(product_branch);
        return product;
    }
    public Node add(Node other) {
        double sum_val = this.value + other.value;
        Node sum = new Node(sum_val);

        Branch sum_branch = new Add(this, other, sum);

        sum.setBranch(sum_branch);
        return sum;
    }


    public void zeroGradients() {
        LinkedList<Branch> queue = new LinkedList<Branch>();

        if (this.branch != null) {
            this.gradient = 0.;
            queue.add(this.branch);

            while (!queue.isEmpty()) {
                Branch current_branch = queue.poll();

                current_branch.zeroGradients(queue);
            }
        }
    }
    public void calculateGradients() {
        LinkedList<Branch> queue = new LinkedList<Branch>();

        if (this.branch != null) {
            this.gradient = 1.;
            queue.add(this.branch);

            while (!queue.isEmpty()) {
                Branch current_branch = queue.poll();

                current_branch.back(queue);
            }
        }
    }
}
