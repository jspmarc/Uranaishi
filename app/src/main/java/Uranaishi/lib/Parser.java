package Uranaishi.lib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class yang digunakan untuk parsing file yang mengandung data {@link Graph}
 * menjadi {@link Graph} sungguhan
 * @author Josep Marcello
 * 25 Februari 2021
 */
public class Parser {
    // *** attribute ***
    String pathToFile;
    FileReader fileReader;
    BufferedReader bufRead;

    // *** Methods ***
    /**
     * Konstruktor parser
     * @param path path ke file yang ingin di parse
     */
    public Parser(String path) {
        pathToFile = path;
    }

    /**
     * Fungsi untuk menge-parse 1 baris dari file menjadi {@link Node} untuk
     * {@link Graph}
     * @param line baris yang ingin di-parse menjadi {@link Node}
     * @return array of array of {@link Node} yang mengandung sudut "utama" di
     * indeks 0 dan sudut yang bertetanggaan di indeks 1. Ukurannya adalah
     * [2][n], n = 1 jika indeks 0, n = bilangan bulat jika indeks 1
     */
    private Node[][] parseLine(String line) {
        Node vertex;

        // Pisahin pada ", "
        String[] vertexesStrings = line.split(", ");

        // ngehapus titik dari vertex kalo ada
        String[] tmp = vertexesStrings[0].split("\\.");
        if (tmp.length == 1) {
            vertex = new Node(tmp[0]);
        } else {
           vertex = new Node(vertexesStrings[0]);
        }

        //
        int i = 1;
        int len = vertexesStrings.length;
        Node[] adjVert = new Node[vertexesStrings.length-1];
        for (; i < len; ++i) {
            // ngehapus titik dari adjacent vertex terakhir
            String[] vs = vertexesStrings[i].split("\\.");
            if (vs.length != 0) {
                adjVert[i-1] = new Node(vs[0]);
            } else {
                adjVert[i-1] = new Node(vertexesStrings[i]);
            }
        }

        Node[][] ret = { {vertex}, adjVert };

        return ret;
    }

    /**
     * Fungsi untuk membuka file dan memasukkanya ke {@link BufferedReader}
     * @throws FileNotFoundException {@link FileNotFoundException}
     * @throws IOException {@link IOException}
     */
    public void openFile() throws FileNotFoundException, IOException {
        fileReader = new FileReader(pathToFile);
        bufRead = new BufferedReader(fileReader);
    }

    /**
     * Fungsi untuk parsing file menjadi {@link Graph}
     * @return {@link Graph} dari hasil bacaan file
     * @throws IOException {@link IOException}
     */
    public Graph parse() throws IOException {
        Graph ret = new Graph();
        String line = bufRead.readLine();
        while (line != null) {
            Node[][] lineParsed = parseLine(line);
            Node vertex = lineParsed[0][0];
            Node[] adjVert = lineParsed[1];

            ret.addVertex(vertex);
            for (Node adj : adjVert) {
                ret.addEdge(vertex, adj);
            }

            line = bufRead.readLine();
        }

        return ret;
    }

    /**
     * Fungsi untuk menutup {@link BufferedReader} dan {@link FileReader} pada
     * Parser
     * @throws IOException {@link IOException}
     */
    public void close() throws IOException {
        bufRead.close();
        fileReader.close();
    }
}
