package Uranaishi;

import Uranaishi.lib.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Berkas java utama untuk aplikasi Uranishi
 * @author Josep Marcello
 * 25 Februari 2021
 */
public class App {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        Parser fp = null;
        Graph g1 = new Graph();

        // buat ngebaca argument dari user
        if (args.length != 0) {
            for (int i = 0; i < args.length; ++i) {
                if (args[i].equals("-f") || args[i].equals("--file")) {
                    fp = new Parser(args[++i]);
                } else if (args[i].equals("--help") || args[i].equals("-h")) {
                    System.out.println("java -jar [nama jar] [--file|-f file-name] [--help]");
                    System.out.println("\t-f\t--file\tArgumen ini diikuti path ke file yang berisi data graf.");
                    System.out.println("\t-h\t--help\tMenuliskan perintah bantuan ini.");
                    System.exit(0);
                }
            }
        }

        // Ngebaca file kalo belom dibaca dari argumen
        if (fp == null) {
            System.out.print("Tuliskan path ke file yang berisi data graf: ");
            String input = scan.nextLine();
            fp = new Parser(input);
        }

        fp.openFile();
        g1 = fp.parse();
        scan.close();
        fp.close();

        g1.print();
        ArrayList<Node> nodes = new ArrayList<Node>();
        g1.topoSort(nodes, 0);
        for (Node node : nodes) {
            System.out.println(node.getInfo());
        }
        System.out.println(nodes.size());
        //g1.print();
    }
}
