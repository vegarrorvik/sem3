import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/**
 * Created by Vegar on 02.11.2015.
 * Imported from prinecton's site. Uses dfs to find and print all paths.
 * source - http://introcs.cs.princeton.edu/java/45graph/AllPaths.java.html
 */
public class AllPaths<Vertex> {

    private Stack<Integer> path  = new Stack<Integer>();   // the current path
    private SET<Integer> onPath  = new SET<Integer>();     // the set of vertices on the path

    public AllPaths(Graph G, Integer s, Integer t) {
        enumerate(G, s, t);
    }

    // use DFS
    private void enumerate(Graph G, int v, int t) {

        // add node v to current path from s
        path.push(v);
        onPath.add(v);

        // found path from s to t - currently prints in reverse order because of stack
        if (v == t)
            StdOut.println(path);

            // consider all neighbors that would continue path with repeating a node
        else {
            for (Integer w : G.adj(v)) {
                if (!onPath.contains(w)) enumerate(G, w, t);
            }
        }

        // done exploring from v, so remove from path
        path.pop();
        onPath.delete(v);
    }

}
