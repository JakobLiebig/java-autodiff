package org.example.autodiff;

import java.util.LinkedList;

public abstract class Branch {
    protected Node[] parents;

    public void zeroGradients(LinkedList<Branch> queue) {
        for (Node parent: parents) {
            parent.setGradient(0.);

            Branch branch = parent.getBranch();

            if (branch != null)
                queue.add(branch);
        }
    };
    abstract public void back(LinkedList<Branch> queue);
}
