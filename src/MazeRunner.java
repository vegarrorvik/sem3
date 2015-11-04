import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import java.io.File;
import java.util.ArrayList;


/**
 * Created by Vegar on 30.10.2015.
 */
public class MazeRunner {
    static ArrayList maze = new ArrayList();
    static File file = new File("compAss3/data/simplemaze20x30.txt");
    static In in = new In(file);
    static Graph graph = new Graph(600);
    static DepthFirstSearch dfs;
    static BreadthFirstPaths bfs;
    static ArrayList<Integer> exits = new ArrayList();


    public static void main(String[]args){
        createMaze();
        printMaze();
        makeEdges();
        dfs();
        findShortestPathToExit();
        System.out.println();

    }

    /**
     * Creates an ArrayList maze, with all vertices
     */
    private static void createMaze(){
        while(in.hasNextChar()) {
            maze.add(in.readChar());
        }

        //Had some problems adding characters, new lines where included, tried to not add them
        //but got into some issues - reason is unknown. Therefore an unsexy method is the result
        for(int i = 0; i<maze.size();i++){
            if(maze.get(i).equals('\n'))
                maze.remove(i);
        }
        //Because screw number 600
        maze.remove(600);
    }

    private static void exits(){
        for (int i = 0; i < maze.size(); i++) {
            if (i < 30 && maze.get(i).equals('1')) {
                exits.add(i);
            }
            if (i > 0)
                if (i % 30 == 0 && maze.get(i - 1).equals('1')) {
                    exits.add(i - 1);
                }
            if (i % 30 == 0 && maze.get(i).equals('1')) {
                exits.add(i);
            }
            if (i > 569 && i < 601 && maze.get(i).equals('1')) {
                exits.add(i);
            }
        }
    }

    private static void findShortestPathToExit(){
        exits();
        BreadthFirstPaths bf;
        int shortestPath = Integer.MAX_VALUE;
        int shortestK = Integer.MAX_VALUE, shortestJ = Integer.MAX_VALUE;
        ArrayList<Integer> sadFace = new ArrayList<>();

        System.out.println("\nShortest path for each pair of exits");
        for(int j:exits) {
            bf = new BreadthFirstPaths(graph,j);
            for(int k:exits) {
                if(j!=k && !(sadFace.contains(j) && sadFace.contains(k)))
                    System.out.println(bf.pathTo(k));
                if(bf.distTo(k)<shortestPath && j!=k) {
                    shortestPath = bf.distTo(k);
                    shortestJ = j; shortestK = k;
                }sadFace.add(k);
            }sadFace.add(j);
        }
        bf = new BreadthFirstPaths(graph,shortestJ);
        System.out.println("\nShortest path is: " + bf.pathTo(shortestK));
    }

    /**
     * Prints the maze in the correct way
     */
    private static void printMaze(){
        for(int i = 0; i<maze.size(); i++){
            if(i%30==0 && i!=0) {
                System.out.print("\n" + maze.get(i));
            }
            else
                System.out.print(maze.get(i));
        }
    }

    /**
     * Creating edges between connected points
     */
    private static void makeEdges(){
        for(int i = 0;i<maze.size();i++){
            if(i>0) {
                if (maze.get(i).equals('1') && maze.get(i - 1).equals('1') && i > 0 && i%30 != 0)
                    graph.addEdge(i, i - 1);
            }
            if(i>29) {
                if (maze.get(i).equals('1') && maze.get(i - 30).equals('1') && i > 29)
                    graph.addEdge(i, i - 30);
            }
        }
    }

    /**
     * A depth first search, prints the number off connected vertices
     */
    private static void dfs(){
        dfs = new DepthFirstSearch(graph,1);
        System.out.println("\n\nNumber of connected vertices is: "+dfs.count());
    }

    /**
     *
     * @param v check if this is connected
     * @return true or false
     */
    private static boolean connected(int v){
        return dfs.marked(v);
    }
}
