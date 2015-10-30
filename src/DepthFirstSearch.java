import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vegar on 30.10.2015. - copied from Algs4 to be able to edit the code
 */
public class DepthFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    /**
     * Computes the vertices in graph <tt>G</tt> that are
     * connected to the source vertex <tt>s</tt>.
     *
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                System.out.println(v + " - " + w);
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     *
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex <tt>s</tt>.
     *
     * @return the number of vertices connected to the source vertex <tt>s</tt>
     */
    public int count() {
        return count;
    }

    /**
     *
     * @return marked for public use
     */
    public boolean[] getMarked(){
        return marked;
    }
}