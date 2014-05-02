package uebung01.a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tobias
 */
public class Histogram {

    private final Graph graph;

    public Histogram() {
        this.graph = new Graph();
    }

    public void readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        br.readLine();
        while((line = br.readLine()) != null){
            String[] split = line.split("\t");
            String a = split[1].split(":")[1];
            String b = split[6].split(":")[1];
            graph.addNode(a);
            graph.addNode(b);
            graph.addEdge(a, b, false);
        }
        br.close();
    }
    
    public void createHistogram(String outFile) throws IOException{
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : graph.getAdjacencyList().entrySet()) {
            int degree = graph.getDegree(entry.getKey());
            if(map.containsKey(degree)){
                map.put(degree, map.get(degree)+1);
            }
            else{
                map.put(degree, 1);
            }
        }
        PrintWriter writer = new PrintWriter(outFile);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            writer.write(entry.getKey() + "\t" + entry.getValue() + "\n");
        }
        writer.close();
    }
    
    public void toDotFormat(String outFile) throws IOException{
        StringBuilder sb = new StringBuilder("graph graphname {\n");
        for (Map.Entry<String, ArrayList<String>> entry : graph.getAdjacencyList().entrySet()) {
            sb.append("_").append(entry.getKey());
            for (String node : entry.getValue()) {
                sb.append(" -- ").append("_").append(node);
            }
            sb.append(";\n");
        }
        sb.append('}');
        PrintWriter writer = new PrintWriter(outFile);
        writer.write(sb.toString());
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Histogram histogram = new Histogram();
        histogram.readFile(args[1]);
        histogram.createHistogram(args[3]+"degreeDistribution.txt");
        histogram.toDotFormat(args[3]+"graph.dot");
    }
}
