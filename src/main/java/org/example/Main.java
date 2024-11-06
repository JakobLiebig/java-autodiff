package org.example;

import org.example.autodiff.Node;

public class Main {
    public static void main(String[] argv) {
        Node a = new Node(2.);
        Node b = new Node(3.);

        Node result = (a.add(b)).multiply(a.add(b));

        System.out.println("a := " + a.getValue());
        System.out.println("b := " + b.getValue());
        System.out.println("r = (a + b) ** 2 = " + result.getValue());

        result.calculateGradients();
        System.out.println("drda = " + a.getGradient());
        System.out.println("drdb = " + b.getGradient());
    }
}