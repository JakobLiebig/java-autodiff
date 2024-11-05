package autodiff;

import java.util.LinkedList;

public abstract class Branch {
    protected Node parents[];

    public void zeroGradients(LinkedList<Branch> queue) {
        for (int i = 0; i < parents.length; i++) {
            Node parent = parents[i];
            parent.setGradient(0.);

            Branch branch = parent.getBranch();
            
            if (branch != null)
                queue.add(branch);
        }
    };
    abstract public void back(LinkedList<Branch> queue);
}
