package uebung01.a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tobias
 */
public class Graph {

    private final HashMap<String, ArrayList<String>> adjacencyList;
    
    public Graph() {
        adjacencyList = new HashMap<>();
    }
    
    public boolean addNode(String node){
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new ArrayList<String>());
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addEdge(String from, String to, boolean directedEdge){
        if(adjacencyList.containsKey(from) && adjacencyList.containsKey(to) && !neighborhood(from, to)){
            adjacencyList.get(from).add(to);
            if(!directedEdge){
                adjacencyList.get(to).add(from);
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    public int countEdges(boolean graphIsDirected){
        int c = 0;
        for (Map.Entry<String, ArrayList<String>> entry : adjacencyList.entrySet()) {
            c += entry.getValue().size();
        }
        return graphIsDirected? c : c/2;
    }
    
    public String[] getNeighbours(String node){
        return adjacencyList.get(node).toArray(new String[adjacencyList.get(node).size()]);
    }
    
    public int getDegree(String node){
        return adjacencyList.get(node).size();
    }
    
    public boolean neighborhood(String u, String v){
        ArrayList<String> list = adjacencyList.get(u);
        for (String node : list) {
            if(node.equals(v)){
                return true;
            }
        }
        return false;
    }
    
    public int size(){
        return adjacencyList.size();
    }

    public HashMap<String, ArrayList<String>> getAdjacencyList() {
        return adjacencyList;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("1");
        g.addNode("2");
        g.addNode("3");
        g.addNode("4");
        g.addEdge("1", "2", true);
        g.addEdge("2", "3", true);
        System.out.println(g.countEdges(true)+" edges");
        System.out.println(g.neighborhood("2", "3"));
    }
    
}
