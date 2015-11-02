import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/**
 * Created by Vegar on 02.11.2015.
 */
public class MST {
    static Graph graph;
    static ArrayList<Vertex> vertexList = new ArrayList<>();
    static File vertexWithWeights = new File("compAss3/data/tinyF_weights.txt");
    static In in = new In(vertexWithWeights);

    public static void main(String[]args){
        readVertex();

    }


    /**
     * I don't know why, but didn't get it to work in any other way. Don't look, ugly code. ugg ugg
     */
    private static void readVertex(){
        int counter = 0;
        while(in.hasNextLine()) {

            String input = in.readLine();
            String[] lol = input.split(" ");

            int i = Integer.parseInt(lol[0]);
            counter++;
            double d = Double.parseDouble(lol[1]);
            counter++;

            vertexList.add(new Vertex(i,d));

            lol = null;
        }

    }

    private static class Vertex{
        int number;
        double weight;

        public Vertex(int number, double weight) {
            this.number = number;
            this.weight = weight;
        }

        public int getNumber() {
            return number;
        }

        public double getWeight() {
            return weight;
        }
    }
}
