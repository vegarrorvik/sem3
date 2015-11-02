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
    static File file = new File("compAss3/data/sm20x30.txt");
    static In in = new In(file);
    static Graph graph = new Graph(600);
    static DepthFirstSearch dfs;
    static BreadthFirstPaths bfs;
    static ArrayList<Integer> exits = new ArrayList();


    public static void main(String[]args){
        createMaze();
        makeEdges();
        dfs();
        findShortestPathToExits();
        System.out.println();
        AllPaths ap = new AllPaths(graph, 1,209);

    }

    /**
     * Creates an ArrayList maze, with all vertices
     */
    private static void createMaze(){
        while(in.hasNextChar()) {
            maze.add(in.readChar());
        }

        //Fikk problem med å gjere dette mens eg las inn
        for(int i = 0; i<maze.size();i++){
            if(maze.get(i).equals('\n'))
                maze.remove(i);
        }
        //Because screw number 600
        maze.remove(600);
    }

    /**
     * Finds possible exits
     */
    private static void findShortestPathToExits(){
        BreadthFirstPaths bf = firstPossibleExits();
        System.out.println("Shortest path to every exits, from the first one found: ");
        for(int i = 0; i<maze.size();i++){
            if(i < 30 && maze.get(i).equals('1')) {
                exits.add(i);
                System.out.println(bf.pathTo(i));
            }
            if(i>0)
                if(i%30==0 && maze.get(i-1).equals('1')) {
                    exits.add(i - 1);
                    System.out.println(bf.pathTo(i - 1));
                }
            if(i%30==0 && maze.get(i).equals('1')) {
                exits.add(i);
                System.out.println(bf.pathTo(i));
            }
            if(i>569 && i<601 && maze.get(i).equals('1')) {
                exits.add(i);
                System.out.println(bf.pathTo(i));
            }
        }/**
        System.out.println("\nPossible exits: ");
        for(int i : exits){
            System.out.println(i);
        }
         */
    }

    private static BreadthFirstPaths firstPossibleExits(){
        BreadthFirstPaths bf;
        for(int i = 0; i<maze.size();i++){
            if(i < 30 && maze.get(i).equals('1'))
               return bf = new BreadthFirstPaths(graph,i);

            if(i>0)
                if(i%30==0 && maze.get(i-1).equals('1'))
                   return bf = new BreadthFirstPaths(graph,i-1);

            if(i%30==0 && maze.get(i).equals('1'))
                return bf = new BreadthFirstPaths(graph,i);

            if(i>569 && i<601 && maze.get(i).equals('1'))
                return bf = new BreadthFirstPaths(graph,i);

        }
        return bf = null;
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
        System.out.println(dfs.count());
    }

    /**
     * Doesn't work at the moment
     */
    private static void printMarked(){
        final String ANSI_RESET = "\u0018[0m";
        final String ANSI_RED = "\u0018[31m";
        for(int i = 0; i<maze.size(); i++){
            if(i%30==0 && i!=0) {
                System.out.println();
                if(dfs.getMarked()[i])
                    System.out.print(ANSI_RED + maze.get(i) + ANSI_RESET);
                else
                    System.out.print(maze.get(i));
            }
            else
                if(dfs.getMarked()[i])
                    System.out.print(ANSI_RED + maze.get(i) + ANSI_RESET);
                else
                    System.out.print(maze.get(i));
        }
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
