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
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        Node n2 = (Node) o;
        return info.equals(n2.info);
    }

    /**
     * Fungsi untuk meng-override fungsi hashcode sehingga Node dapat digunakan
     * untuk key pada collection Map
     */
    @Override
    public int hashCode() {
        return this.info.hashCode();
    }
}
