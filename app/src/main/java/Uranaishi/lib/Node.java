package Uranaishi.lib;

/**
 * Kelas yang merepresentasikan node (sudut) pada graf
 * @author Josep Marcello
 * 25 Februari 2021
 */
public class Node {
    // *** attribute ***
    private String info;

    // *** Getters and setters ***
    /**
     * Getter info attribute
     * @return info (nama) node
     */
    public String getInfo() {
        return this.info;
    }

    // *** Methods **
    /**
     * Konstruktor untuk class Node
     * @param info isi (nama) node
     */
    public Node(String info) {
        this.info = info;
    }

    /**
     * Fungsi untuk membandingkan node "this" dengan node lain
     * @param n2 node lain yang ingin dibandingkan dengan node "this"
     * @return true jika kedua node sama, false jika tidak
     */
    public boolean equals(Node n2) {
        return info.equals(n2.info);
    }
}
