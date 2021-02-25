package Uranaishi.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

/**
 * Class yang merepresentasikan DAG yg memanfaatkan adjacency list
 * ({@link ArrayList}), {@link HashMap}, dan {@link Node}
 * @author Josep Marcelo
 * 25 Februari 2021
 */
public class Graph {
    // *** attribute ***
    /// adjacency list untuk graf
    private HashMap<Node, ArrayList<Node>> nodes;

    // *** Getters and setters ***

    // *** Methods **

    /**
     * Konstruktor graf kosong
     */
    public Graph() {
        nodes = new HashMap<>();
    }

    /**
     * Konstruktor graf berisi
     * Graf yang dibentuk adalah DAG dengan sisi e dan sudut v
     * @param v sisi-sisi pada DAG
     * @param e informasi sudut pada DAG [src vertex, dest vertex], pasti
     * berukuran [n][2], n sebuah integer
     */
    public Graph(Node[] v, Node[][] e) {
        nodes =  new HashMap<>();
        for (int i = 0; i < v.length; ++i) {
            addVertex(v[i]);
        }

        for (Node[] adjNodes : e) {
            addEdge(adjNodes[0], adjNodes[1]);
        }
    }

    /**
     * Fungsi untuk menambahkan sebuah sisi berarah antara 2 sudut. Untuk
     * kebutuhan aplikasi Uranaishi, jika sudut asal dan sudut tujuan sama,
     * maka tidak akan dilakukan apa-apa. Selain itu, jika sudut asal tidak ada
     * di graf, maka akan ditambahkan secara otomatis ke graf
     * @param src sudut asal
     * @param dest sudut tujuan
     */
    public void addEdge(Node src, Node dest) {
        if (src != dest) {
            ArrayList<Node> adjList = nodes.get(src);
            if (adjList == null) {
                adjList = new ArrayList<Node>();
                nodes.put(src, adjList);
            }

            adjList.add(dest);
        }
    }

    /**
     * Fungsi untuk menambahkan sebuah sudut tanpa sisi ke graf. Jika sudut
     * sudah ada, tidak akan dilakukan apa-apa
     * @param n1 sudut yang akan ditambahkan ke graf
     */
    public void addVertex(Node n1) {
        if (!nodes.containsKey(n1)) {
            nodes.put(n1, new ArrayList<Node>());
        }
    }

    /**
     * Fungsi untuk menuliskan isi DAG (ditunjukkan sebagai adjacency list)
     */
    public void print() {
        Iterator<Map.Entry<Node, ArrayList<Node>>> it = nodes.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Node, ArrayList<Node>> node =
                (Map.Entry<Node, ArrayList<Node>>) it.next();

            ArrayList<Node> adjcentVertexes = node.getValue();
            int adjacentVertexCount = adjcentVertexes.size();
            Node vertex = node.getKey();

            if (adjacentVertexCount != 0) {
                System.out.print(vertex.getInfo() + "->");
            } else {
                System.out.print(vertex.getInfo());
            }

            int i = 0;
            for (Node adjacentVertex : adjcentVertexes) {
                if (i++ != adjacentVertexCount-1) {
                    System.out.print(adjacentVertex.getInfo() + "->");
                } else {
                    System.out.print(adjacentVertex.getInfo());
                }
            }
            System.out.println();
        }
    }
}
