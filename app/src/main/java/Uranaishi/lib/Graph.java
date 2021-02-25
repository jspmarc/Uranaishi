/**
 * Helper library to make a DAG using adjacency list and hashmap
 * Josep Marcello
 * 25 February 2020
 */

package Uranaishi.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class Graph {
    // *** attribute ***
    /// adjacency list of the graph
    private HashMap<Node, ArrayList<Node>> nodes = new HashMap<>();

    // *** Getters and setters ***

    // *** Methods **
    /**
     * Graph constructor
     * The constructed graph is a DAG with only vertexes and no edges
     * @param n array of nodes, contains the vertexes of the graph
     */
    public Graph(Node ...n) {
        for (int i = 0; i < n.length; ++i) {
            addNode(n[i]);
        }
    }

    /**
     * Function to add a directed edge between 2 vertexes
     * @param src source vertex
     * @param dest destination vertex
     */
    public void addEdge(Node src, Node dest) {
        ArrayList<Node> adjList = nodes.get(src);
        if (adjList == null) {
            adjList = new ArrayList<Node>();
            nodes.put(src, adjList);
        }

        adjList.add(dest);
    }

    /**
     * Function to add a vertex to the graph
     * @param n1 node/vertex to be added to the graph
     */
    public void addNode(Node n1) {
        nodes.put(n1, new ArrayList<Node>());
    }

    /**
     * Function to write the contents of the graph (shown as an adjacency
     * list)
     */
    public void print() {
        Iterator<Map.Entry<Node, ArrayList<Node>>> it = nodes.entrySet().iterator();

        do {
            Map.Entry<Node, ArrayList<Node>> node = (Map.Entry<Node, ArrayList<Node>>) it.next();

            ArrayList<Node> adjcentVertexes = node.getValue();
            int adjacentVertexCount = adjcentVertexes.size();
            Node vertex = node.getKey();

            if (adjacentVertexCount != 0) {
                System.out.print(vertex.getName() + " -> ");
            } else {
                System.out.print(vertex.getName());
            }

            int i = 0;
            for (Node adjacentVertex : adjcentVertexes) {
                if (i++ != adjacentVertexCount-1) {
                    System.out.print(adjacentVertex.getName() + " -> ");
                } else {
                    System.out.print(adjacentVertex.getName());
                }
            }
            System.out.println();
        } while (it.hasNext());
    }
}
