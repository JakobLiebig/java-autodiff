package org.example.autodiff;

import org.example.autodiff.Node;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    void testMultiplyGradients() {
        double a_value = 2.0;
        double b_value = 3.0;

        Node a = new Node(a_value);
        Node b = new Node(b_value);

        // r = ((a * b) * b) * a
        Node r = ((a.multiply(b)).multiply(b)).multiply(a);

        r.calculateGradients();

        // drda = 2 * a * b^2
        // drdb = 2 * a^2 * b
        double a_gradient = 2. * a_value * b_value * b_value;
        double b_gradient = 2. * a_value * a_value * b_value;
        assertEquals(a.getGradient(), a_gradient, "drda should be " + a_gradient);
        assertEquals(b.getGradient(), b_gradient, "drdb should be " + b_gradient);
    }

    @Test
    void testAddGradients() {
        double a_value = 2.0;
        double b_value = 3.0;

        Node a = new Node(a_value);
        Node b = new Node(b_value);

        // r = (a + b) + b
        Node r = (a.add(b)).add(b);

        // drda = 1
        // drdb = 2
        r.calculateGradients();
        assertEquals(a.getGradient(), 1., "drda should be 1.");
        assertEquals(b.getGradient(), 2., "drdb should be 2.");
    }

    @Test
    void testZerograd() {
        double a_value = 2.0;
        double b_value = 3.0;

        Node a = new Node(a_value);
        Node b = new Node(b_value);

        Node r = (a.add(b)).multiply((a.add(b)));

        r.calculateGradients();
        r.zeroGradients();

        assertEquals(a.getGradient(), 0., "drda should be 0.");
        assertEquals(b.getGradient(), 0., "drdb should be 0.");
    }

    @Test
    void testRepeatedGradCalculation() {
        double a_value = 2.0;
        double b_value = 3.0;

        Node a = new Node(a_value);
        Node b = new Node(b_value);

        Node r = (a.add(b)).multiply((a.add(b)));

        r.calculateGradients();

        // remember first gradients
        double a_first_gradient = a.getGradient();
        double b_first_gradient = b.getGradient();

        r.zeroGradients();
        r.calculateGradients();

        double a_second_gradient = a.getGradient();
        double b_second_gradient = b.getGradient();

        assertEquals(a_second_gradient, a_first_gradient, "gradients drda dont match over two calculations");
        assertEquals(b_second_gradient, b_first_gradient, "gradients drdb dont match over two calculations");
    }
}