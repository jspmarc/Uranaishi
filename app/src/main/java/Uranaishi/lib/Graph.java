/**
 * Library bantuan untuk membuat graph berarah dengan adjacency list dan
 * hashmap.
 * Josep Marcello
 * 25 Februari 2020
 */

package Uranaishi.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class Graph {
    // *** attribute ***
    private HashMap<Node, ArrayList<Node>> nodes = new HashMap<>();

    // *** Getters and setters ***

    // *** Methods **
    /**
     * Konstruktor graf
     * Graf yang dibuat adalah graf berarah yang hanya memiliki vertes (sudut),
     * tapi tidak memiliki edge (sisi)
     * @param n array of nodes yang berisi sudut-sudut
     */
    public Graph(Node ...n) {
        for (int i = 0; i < n.length; ++i) {
            addNode(n[i]);
        }
    }

    /**
     * Fungsi untuk menambahkan sisi/edge berarah antara 2 sisi
     * @param src graf asal
     * @param dest graf tujuan
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
     * Fungsi untuk menambahkan sudut/vertex ke graf
     * @param n1 node (sudut) yang ingin ditambahkan ke graf
     */
    public void addNode(Node n1) {
        nodes.put(n1, new ArrayList<Node>());
    }

    /**
     * Fungsi untuk menuliskan isi graf (ditampilkan sebagai adjacency list)
     */
    public void print() {
        Iterator<Map.Entry<Node, ArrayList<Node>>> it = nodes.entrySet().iterator();

        do {
            Map.Entry<Node, ArrayList<Node>> node = (Map.Entry<Node, ArrayList<Node>>) it.next();

            ArrayList<Node> adjcentVertexes = node.getValue();
            int banyakAdjacentVertex = adjcentVertexes.size();
            Node vertex = node.getKey();

            if (banyakAdjacentVertex != 0) {
                System.out.print(vertex.getName() + " -> ");
            } else {
                System.out.print(vertex.getName());
            }

            int i = 0;
            for (Node adjacentVertex : adjcentVertexes) {
                if (i++ != banyakAdjacentVertex-1) {
                    System.out.print(adjacentVertex.getName() + " -> ");
                } else {
                    System.out.print(adjacentVertex.getName());
                }
            }
            System.out.println();
        } while (it.hasNext());
    }
}
