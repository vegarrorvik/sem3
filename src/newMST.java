import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Vegar on 03.11.2015.
 */
public class newMST {
    static HashMap<Integer,Double> weights = new HashMap<>();
    static File weightFile = new File("compAss3/data/tinyF_weights.txt");
    static File tinyF = new File("compAss3/data/tinyF.txt");
    static In in = new In(tinyF);
    static int V, E;
    static EdgeWeightedGraph graph;


    private static void readWeights(File file){
        In in = new In(file);
        while(in.hasNextLine()){
            String input = in.readLine();
            String[] lol = input.split(" ");

            int vertex = Integer.parseInt(lol[0]);
            double weight = Double.parseDouble(lol[1]);

            weights.put(vertex, weight);

            lol = null;
        }
    }

    private static EdgeWeightedGraph readEdges(File file){
        In in = new In(file);
        int V = in.readInt();
        EdgeWeightedGraph graph = new EdgeWeightedGraph(V+1);

        int E = in.readInt();
        for(int i = 0; i<E; i++){
            int v = in.readInt();
            int w = in.readInt();
            double weight = weights.get(v) + weights.get(w);
            Edge e = new Edge(v,w,weight);
            graph.addEdge(e);
        }
        return graph;
    }

    public static void main(String[]args){
        readWeights(weightFile);
        graph = readEdges(tinyF);

        PrimMST pmst = new PrimMST(graph);

        for(Edge e : pmst.edges()){
            System.out.println(e + " " + pmst.weight());
        }
    }
}
