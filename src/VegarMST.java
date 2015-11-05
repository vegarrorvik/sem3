import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Vegar on 03.11.2015.
 * Where I create EdgeWeightedGraph, connects MST's, prints trees and makes the weight hashmap.
 * Main at the bottomn
 */
public class VegarMST {
    static HashMap<Integer,Double> weights = new HashMap<>();
    static File weightFile = new File("compAss3/data/tinyF_weights.txt");
    static File tinyF = new File("compAss3/data/tinyF.txt");
    static EdgeWeightedGraph graph;

    /**
     * Saves the weight of each node in a hashmap, potato style!
     * @param file
     */
    private static void readWeights(File file){
        In in = new In(file);
        while(in.hasNextLine()){
            String input = in.readLine();
            String[] lol = input.split(" ");

            int vertex = Integer.parseInt(lol[0]);
            double weight = Double.parseDouble(lol[1]);

            weights.put(vertex, weight);
        }
    }

    /**
     * Makes the edgeWeightedTree to use in PrimMST
     * @param file to make the EdgeWeightedTree from
     * @return an edgeWeightedTree
     */
    private static EdgeWeightedGraph readEdges(File file){
        In in = new In(file);
        int V = in.readInt();
        EdgeWeightedGraph graph = new EdgeWeightedGraph(V+1);

        int E = in.readInt();
        for(int i = 0; i<E; i++){
            int v = in.readInt();
            int w = in.readInt();
            double weight = weights.get(v) + weights.get(w);
            Edge e = new Edge(v,w,weight){
                @Override
                public String toString() {
                    return String.format("%d-%d     %.2f", v, w, weight);
                }
            };
            graph.addEdge(e);
        }
        return graph;
    }

    private static void printTrees(PrimMST pmst){
        for(int i = 1; i<pmst.subtrees.size();i++){
            System.out.println();
            for(int j = 0; j<pmst.subtrees.get(i).size();j++){
                System.out.println(pmst.subtrees.get(i).get(j));
            }
        }
    }

    /**
     *
     * @param graph the graph to connect the different MST's
     * @param mst the mst where the specific info is saved, instanciated in main
     */
    private static void connectTrees(EdgeWeightedGraph graph, PrimMST mst){
        mst.minimumVertices.remove(0);
        Integer minVertex = mst.minimumVertices.stream().min((n,m)->weights.get(n).compareTo(weights.get(m))).get();

        for(Integer i : mst.minimumVertices){
            if(i != minVertex){
                double weight = weights.get(i) + weights.get(minVertex);
                Edge e = new Edge(i,minVertex, weight){
                    @Override
                    public String toString() {
                        return String.format("%d-%d     %.2f", i, minVertex, weight);
                    }
                };
                graph.addEdge(e);
            }
        }
    }

    public static void main(String[]args){
        Stopwatch stopwatch = new Stopwatch();
        readWeights(weightFile);
        graph = readEdges(tinyF);

        PrimMST pmst = new PrimMST(graph);
        int numberOfTrees = pmst.numberOfTrees();

       // printTrees(pmst);

        connectTrees(graph,pmst);
        pmst = new PrimMST(graph);

        System.out.println("Total weight: " + pmst.weight() + "\nNumber of trees: " + numberOfTrees);
        System.out.println("Elapsed time: " + stopwatch.elapsedTime());
    }
}
