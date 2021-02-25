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
    /// node batas yang menandakan antarrekursi topology sort
    public Node nodeBatas = new Node("bruh moment");

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
        for (Node[] adjNodes : e) {
            // add edge otomatis nambahin vertex kalo vertex-nya belom ada
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
        Iterator<Map.Entry<Node, ArrayList<Node>>> it =
            nodes.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Node, ArrayList<Node>> node =
                (Map.Entry<Node, ArrayList<Node>>) it.next();

            ArrayList<Node> adjcentVertexes = node.getValue();
            int adjacentVertexCount = adjcentVertexes.size();
            Node vertex = node.getKey();

            // tulis vertex
            if (adjacentVertexCount != 0) {
                System.out.print(vertex.getInfo() + "->");
            } else {
                System.out.print(vertex.getInfo());
            }

            // tulis sudut-sudut yang bertetanggaan dengan vertex
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

    private void removeOccurance(Node n) {
        Iterator<Map.Entry<Node, ArrayList<Node>>> it =
            nodes.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Node, ArrayList<Node>> nodeEntry =
                (Map.Entry<Node, ArrayList<Node>>) it.next();

            ArrayList<Node> adjVert = nodeEntry.getValue();

            if (nodeEntry.getKey().equals(n)) {
                continue;
            }

            Iterator<Node> itN = adjVert.iterator();
            while(itN.hasNext()) {
                Node vert = itN.next();
                if (vert.equals(n)) {
                    itN.remove();
                }
            }
        }
    }

    public void topoSort(ArrayList<Node> ret, int iter) {
        if (nodes.isEmpty() || iter == 4) return;
        ArrayList<Node> takenNow = new ArrayList<>();
        Iterator<Map.Entry<Node, ArrayList<Node>>> it =
            nodes.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Node, ArrayList<Node>> nodeEntry =
                (Map.Entry<Node, ArrayList<Node>>) it.next();

            ArrayList<Node> adjVert = nodeEntry.getValue();
            int len = adjVert.size();

            // cek udah ambil prereq atau belom
            boolean hasTakenPrereq = len == 0;
            if (!hasTakenPrereq) {
                hasTakenPrereq = true;
                for (Node vert : adjVert) {
                    if (!ret.contains(vert)) {
                        System.out.println("=====================");
                        System.out.print(vert.getInfo() + '\t');
                        System.out.println("\n=====================");
                        hasTakenPrereq = false;
                        break;
                    }
                }
            }

            if (hasTakenPrereq) {
                System.out.println("======");
                print();
                takenNow.add(nodeEntry.getKey());
                it.remove();
                System.out.println("------");
                print();
                System.out.println("======");
            }

            //for (Node node : ret) {
                //System.out.println(node.getInfo());
            //}

            //removeOccurance(nodeEntry.getKey());
        }

        // ulangi toposort
        ret.addAll(takenNow);
        ret.add(nodeBatas);
        topoSort(ret, ++iter);
    }
}
