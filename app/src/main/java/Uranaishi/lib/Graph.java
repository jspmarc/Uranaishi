package Uranaishi.lib;

import java.util.HashMap;
import java.util.Iterator;
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
    private HashMap<Node, ArrayList<Node>> inEdges;

    // *** Getters and setters ***

    // *** Methods **
    /**
     * Konstruktor graf kosong
     */
    public Graph() {
        inEdges = new HashMap<>();
    }

    /**
     * Konstruktor graf berisi
     * Graf yang dibentuk adalah DAG dengan sisi e dan sudut v
     * @param v sisi-sisi pada DAG
     * @param e informasi sudut pada DAG [src vertex, dest vertex], pasti
     * berukuran [n][2], n sebuah integer
     */
    public Graph(Node[] v, Node[][] e) {
        inEdges =  new HashMap<>();
        for (Node[] adjNodes : e) {
            // add edge otomatis nambahin vertex kalo vertex-nya belom ada
            addEdge(adjNodes[0], adjNodes[1]);
        }
    }

    /**
     ` Fungsi untuk menambahkan sebuah sisi berarah antara 2 sudut. Jika sudut
     * asal dan sudut tujuan sama, maka tidak akan dilakukan apa-apa. Selain
     * itu, jika sudut asal tidak ada di graf, maka akan ditambahkan secara
     * otomatis ke graf
     * @param src sudut asal
     * @param dest sudut tujuan
     */
    public void addEdge(Node src, Node dest) {
        if (src != dest) {
            ArrayList<Node> adjList = inEdges.get(src);
            if (adjList == null) {
                adjList = new ArrayList<Node>();
                inEdges.put(src, adjList);
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
        if (!inEdges.containsKey(n1)) {
            inEdges.put(n1, new ArrayList<Node>());
        }
    }

    /**
     * Fungsi untuk menuliskan isi DAG (ditunjukkan sebagai adjacency list)
     */
    public void print() {
        for (Node vert: inEdges.keySet()) {
            ArrayList<Node> adjcentVertexes = inEdges.get(vert);
            int adjacentVertexCount = adjcentVertexes.size();

            // tulis vertex
            if (adjacentVertexCount != 0) {
                System.out.print(vert.getInfo() + "->");
            } else {
                System.out.print(vert.getInfo());
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

    /**
     * Fungsi untuk menghapus sebuah vertex dari graf
     * @param n vertex yang ingin dihapus
     */
    private void removeVertex(Node n) {
        // iterasi key-nya
        Iterator<Node> itV = inEdges.keySet().iterator();
        while (itV.hasNext()) {
            Node vert = itV.next();
            ArrayList<Node> adjVerts = inEdges.get(vert);

            // kalau key-nya adalah elemen yang mau di-remove, remove vertex
            if (vert.equals(n)) {
                itV.remove();
            }

            // iterasi adjacency list setiap sudut
            Iterator<Node> itN = adjVerts.iterator();
            while(itN.hasNext()) {
                Node adjVert = itN.next();
                if (adjVert.equals(n)) {
                    // hapus kalo ada vertex n di dalem adjacency list
                    itN.remove();
                }
            }
        }
    }

    /**
     * Fungsi untuk mengurutkan graf dengan topological sort. PERHATIAN: fungsi
     * ini akan menghapus isi graf.
     * @return Urutan vertexes berdasarkan requirements yang sudah
     * dipisah-pisah
     */
    public ArrayList<ArrayList<Node>> topoSort() {
        if (inEdges.isEmpty()) {
            return new ArrayList<ArrayList<Node>>();
        }

        ArrayList<Node> takenNow = new ArrayList<>();
        ArrayList<ArrayList<Node>> ret = new ArrayList<>();

        // iterasiin vertices-nya
        for (Node vert: inEdges.keySet()) {
            ArrayList<Node> adjVert = inEdges.get(vert);

            // kalo ga ada adjacent vertex, tambahin vertex tadi ke list
            if (adjVert.isEmpty()) {
                takenNow.add(vert);
            }
        }

        // hapus vertex yang sudah "diambil"
        // bagian decrease
        for (Node vert : takenNow) {
            removeVertex(vert);
        }

        ret.add(takenNow);
        // ulangi toposort
        // bagian conquer
        ret.addAll(topoSort());

        return ret;
    }
}
